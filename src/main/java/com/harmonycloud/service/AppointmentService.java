package com.harmonycloud.service;

import com.harmonycloud.bo.AppointmentAttend;
import com.harmonycloud.bo.AppointmentBo;
import com.harmonycloud.bo.AppointmentByMonth;
import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.dto.AppointmentQuotaDto;
import com.harmonycloud.entity.Appointment;
import com.harmonycloud.entity.AppointmentQuota;
import com.harmonycloud.entity.Encounter;
import com.harmonycloud.repository.AppointmentRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import org.aspectj.apache.bcel.classfile.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AppointmentService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
//    private JwtUtil jwtUtil = new JwtUtil();

    @Resource
    private AppointmentRepository appointmentRepository;

    @Resource
    private AppointmentQuotaService appointmentQuotaService;

    @Resource
    private EncouterService encouterService;

    @Resource
    private HolidayService holidayService;

    public Result getQuotaList(AppointmentByMonth appointmentByMonth) {
        try{
            //获取该年月下的所有假期，并放入set集合中
            Set<String> holidayDateSet = holidayService.getHolidayDate();

            //获取该年月下的所有预约额度
            List<AppointmentQuota> appointmentQuotaBoList = appointmentQuotaService.getAppointmentQuotaList(appointmentByMonth);

            List<AppointmentQuotaDto> appointmentQuotaDtoList = new ArrayList<>();
            for (AppointmentQuota aqb: appointmentQuotaBoList) {
                AppointmentQuotaDto appointmentQuotaDto = new AppointmentQuotaDto();
                appointmentQuotaDto.setAppointmentQuota(aqb);
                if (holidayDateSet.contains(aqb.getAppointmentDate())) {
                    appointmentQuotaDto.setHolidayDate(true);
                } else {
                    appointmentQuotaDto.setHolidayDate(false);
                }
            }
            return Result.buildSuccess(appointmentQuotaDtoList);
        }catch (Exception e){
            e.printStackTrace();
            return Result.buildError(CodeMsg.DATA_QUERY_ERROR);
        }
    }


    public Result getAppointmentList(Integer patientId) {
        List<Appointment> appoinmentDtoList = null;
        try {
            appoinmentDtoList = appointmentRepository.findByPatientId(patientId);
            if (appoinmentDtoList.size() == 0 || appoinmentDtoList == null) {
                return Result.buildError(CodeMsg.PATIENT_NOT_EXIST);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Result.buildError(CodeMsg.DATA_QUERY_ERROR);
        }
        return Result.buildSuccess(appoinmentDtoList);
    }

    public Result bookAppointment(AppointmentBo appointmentBo) {
        try {
            Integer patientId = appointmentBo.getPatientId();
            Integer clinicId = appointmentBo.getClinicId();
            Integer typeId = appointmentBo.getEncounterTypeId();
            Integer roomId = appointmentBo.getRoomId();
            String date = appointmentBo.getDate();
            Appointment appointment = new Appointment(patientId, clinicId, typeId, roomId, date, "Not Attend");
            appointmentRepository.save(appointment);
            return Result.buildSuccess(appointment);
        } catch (Exception e) {
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }

    }

    public Result isDuplicated(Integer patientId, Integer typeId, Integer roomId) {
        try {
            List<Appointment> appointments = appointmentRepository.findByPatientIdAndEncounterTypeIdAndRoomIdAndAttendanceStatus(patientId, typeId, roomId, "Not Attend");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            Date nowtime = sdf.parse(sdf.format(new Date()));
            for (int i = 0; i < appointments.size(); i++) {
                Date appointmentTime = sdf.parse(appointments.get(i).getAppointmentDate());
                int flag = appointmentTime.compareTo(nowtime);
                if (flag > 0) {
                    return Result.buildError(CodeMsg.DUPLICATED_BOOKING);
                }
            }
            return Result.buildSuccess(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
    }

    public Result markAttendence(Integer appointmentId) {
        DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm",Locale.ENGLISH);
        try {
            Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
            appointment.get().update("Attend");
            Date date = new Date();
            appointment.get().setAttendanceTime(sdf.format(date));
            appointmentRepository.save(appointment.get());
            Encounter encounter = new Encounter(appointment.get().getPatientId(),appointment.get().getEncounterTypeId(),appointment.get().getClinicId(),appointment.get().getRoomId(),sdf.format(date),appointmentId);
            encouterService.save(encounter);
            return Result.buildSuccess(encounter);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }

    }


    public Result getAttendList(AppointmentAttend appointmentAttend) {
        List<Appointment> appointmentList = null;
        try {
            if (appointmentAttend.getRoomId() == 0 && "All".equals(appointmentAttend.getAttendanceStatus())) {
                appointmentList = appointmentRepository.findByDate(appointmentAttend.getAppointmentDate());
            } else if (appointmentAttend.getRoomId() != 0 && "All".equals(appointmentAttend.getAttendanceStatus())) {
                appointmentList = appointmentRepository.findByDateRoom(appointmentAttend.getRoomId(),
                        appointmentAttend.getAppointmentDate());
            } else if (appointmentAttend.getRoomId() == 0 && !"All".equals(appointmentAttend.getAttendanceStatus())) {
                appointmentList = appointmentRepository.findByDateStatus(appointmentAttend.getAttendanceStatus(),
                        appointmentAttend.getAppointmentDate());
            } else {
                appointmentList = appointmentRepository.findByDateStatusRoom(appointmentAttend.getRoomId(),
                        appointmentAttend.getAttendanceStatus(), appointmentAttend.getAppointmentDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.buildError(CodeMsg.DATA_QUERY_ERROR);
        }
        return Result.buildSuccess(appointmentList);
    }
}
