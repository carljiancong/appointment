package com.harmonycloud.service;

import com.harmonycloud.config.EncounterConfigurationProperties;
import com.harmonycloud.dto.ResponseDto;
import com.harmonycloud.entity.Appointment;
import com.harmonycloud.entity.Encounter;
import com.harmonycloud.repository.AppointmentRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.vo.AppointmentAttend;
import com.harmonycloud.vo.AppointmentVo;
import org.apache.servicecomb.saga.omega.context.annotations.SagaStart;
import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeoutException;

@Service
public class AppointmentService {
    private Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private SyncService syncService;

    @Autowired
    private EncounterConfigurationProperties config;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentQuotaService appointmentQuotaService;


    public Result getAppointmentHistory(Integer patientId) {
        List<Appointment> appoinmentList = null;
        try {
            appoinmentList = appointmentRepository.findByPatientId(patientId);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Result.buildError(CodeMsg.DATA_QUERY_ERROR);
        }
        return Result.buildSuccess(appoinmentList);
    }

    public Result bookAppointment(AppointmentVo appointmentVo) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Integer patientId = appointmentVo.getPatientId();
            Integer clinicId = appointmentVo.getClinicId();
            Integer typeId = appointmentVo.getEncounterTypeId();
            Integer roomId = appointmentVo.getRoomId();
            Date date = appointmentVo.getDate();
            String patientDoc = appointmentVo.getPatientDoc();
            String patientName = appointmentVo.getPatientName();
            String encounterTypeName = appointmentVo.getEncounterTypeName();
            String roomName = appointmentVo.getRoomName();
            String clinicName = appointmentVo.getClinicName();
            Appointment appointment = new Appointment(patientId, clinicId, typeId, roomId, date, "Not Attend", patientDoc, patientName, encounterTypeName, roomName, clinicName);
            appointmentRepository.save(appointment);
            appointmentQuotaService.updateAppointmentQuotaList(sdf.format(appointment.getAppointmentDate()), clinicId, typeId, roomId);
            return Result.buildSuccess(appointment);
        } catch (Exception e) {
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
    }

    public Result isDuplicated(Integer clinicId, Integer patientId, Integer typeId, Integer roomId, Date date) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Boolean isfull = appointmentQuotaService.isFull(sdf.format(date), clinicId, typeId, roomId);
            if (!isfull) {
                List<Appointment> appointments = appointmentRepository.findByPatientIdAndEncounterTypeIdAndRoomIdAndAttendanceStatus(patientId, typeId, roomId, "Not Attend");
                Date nowtime = sdf.parse(sdf.format(new Date()));
                for (int i = 0; i < appointments.size(); i++) {
                    Date appointmentTime = appointments.get(i).getAppointmentDate();
                    int flag = appointmentTime.compareTo(nowtime);
                    if (flag >= 0) {
                        return Result.buildError(CodeMsg.DUPLICATED_BOOKING);
                    }
                }
                return Result.buildSuccess(null);
            } else {
                return Result.buildError(CodeMsg.Full);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @SagaStart
    public Result markAttendance(Integer appointmentId) throws Exception {
        Appointment appointment = null;
        ResponseDto encounterResponse = null;
        try {
            appointment = appointmentRepository.findByAppointmentId(appointmentId);
            appointment.update("Attend");
            Date date = new Date();
            appointment.setAttendanceTime(date);
            appointmentRepository.save(appointment);
            Encounter encounter = new Encounter(appointment.getPatientId(), appointment.getEncounterTypeId(), appointment.getClinicId(),
                    appointment.getRoomId(), date, appointmentId);
            encounterResponse = syncService.save(config.getEncounterUri(), encounter);
        } catch (TimeoutException e) {
            throw new RuntimeException("timeout", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("attend error");
        }
        return Result.buildSuccess(encounterResponse);
    }

//    public void attendanceCancel(Integer appointmentId) {
//        try {
//            Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
//            appointment.setAttendanceStatus("Not Attend");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public Result getAppointmentList(AppointmentAttend appointmentAttend) {
        List<Appointment> appointmentList = null;
        try {
            if (appointmentAttend.getRoomId() == 0 && "All".equals(appointmentAttend.getAttendanceStatus())) {
                appointmentList = appointmentRepository.findByappointmentDate(appointmentAttend.getAppointmentDate());
            } else if (appointmentAttend.getRoomId() != 0 && "All".equals(appointmentAttend.getAttendanceStatus())) {
                appointmentList = appointmentRepository.findByroomIdAndDate(appointmentAttend.getRoomId(),
                        appointmentAttend.getAppointmentDate());
            } else if (appointmentAttend.getRoomId() == 0 && !"All".equals(appointmentAttend.getAttendanceStatus())) {
                appointmentList = appointmentRepository.findBystatusAndDate(appointmentAttend.getAttendanceStatus(),
                        appointmentAttend.getAppointmentDate());
            } else {
                appointmentList = appointmentRepository.findByroomIdAndSatusAndDate(appointmentAttend.getRoomId(),
                        appointmentAttend.getAttendanceStatus(), appointmentAttend.getAppointmentDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.buildError(CodeMsg.DATA_QUERY_ERROR);
        }
        return Result.buildSuccess(appointmentList);
    }
}
