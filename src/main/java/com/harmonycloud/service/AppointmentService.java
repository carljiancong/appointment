package com.harmonycloud.service;

import com.harmonycloud.entity.Appointment;
import com.harmonycloud.entity.Encounter;
import com.harmonycloud.repository.AppointmentRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.vo.AppointmentAttend;
import com.harmonycloud.vo.AppointmentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AppointmentService {
    private Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EncouterService encouterService;

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
        DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        try {
            Integer patientId = appointmentVo.getPatientId();
            Integer clinicId = appointmentVo.getClinicId();
            Integer typeId = appointmentVo.getEncounterTypeId();
            Integer roomId = appointmentVo.getRoomId();
            String date = appointmentVo.getDate();
            String patientDoc = appointmentVo.getPatientDoc();
            String patientName = appointmentVo.getPatientName();
            String encounterTypeName = appointmentVo.getEncounterTypeName();
            String roomName = appointmentVo.getRoomName();
            String clinicName = appointmentVo.getClinicName();
            Appointment appointment = new Appointment(patientId, clinicId, typeId, roomId, date, "Not Attend", patientDoc, patientName, encounterTypeName, roomName, clinicName);
            appointmentRepository.save(appointment);
            appointmentQuotaService.updateAppointmentQuotaList(sdf.format(sdf.parse(appointment.getAppointmentDate())),clinicId,typeId,roomId);
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
                if (flag >= 0) {
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
        DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.ENGLISH);
        try {
            Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
            appointment.get().update("Attend");
            Date date = new Date();
            appointment.get().setAttendanceTime(sdf.format(date));
            appointmentRepository.save(appointment.get());
            Encounter encounter = new Encounter(appointment.get().getPatientId(), appointment.get().getEncounterTypeId(), appointment.get().getClinicId(), appointment.get().getRoomId(), sdf.format(date), appointmentId);
            encouterService.save(encounter);
            return Result.buildSuccess(encounter);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }

    }


    public Result getAppointmentList(AppointmentAttend appointmentAttend) {
        List<Appointment> appointmentList = null;
        try {
            if (appointmentAttend.getRoomId() == 0 && "All".equals(appointmentAttend.getAttendanceStatus())) {
                appointmentList = appointmentRepository.findByAppointmentDateContaining(appointmentAttend.getAppointmentDate());
            } else if (appointmentAttend.getRoomId() != 0 && "All".equals(appointmentAttend.getAttendanceStatus())) {
                appointmentList = appointmentRepository.findByRoomIdAndAppointmentDateContaining(appointmentAttend.getRoomId(),
                        appointmentAttend.getAppointmentDate());
            } else if (appointmentAttend.getRoomId() == 0 && !"All".equals(appointmentAttend.getAttendanceStatus())) {
                appointmentList = appointmentRepository.findByAttendanceStatusAndAppointmentDateContaining(appointmentAttend.getAttendanceStatus(),
                        appointmentAttend.getAppointmentDate());
            } else {
                appointmentList = appointmentRepository.findByRoomIdAndAttendanceStatusAndAppointmentDateContaining(appointmentAttend.getRoomId(),
                        appointmentAttend.getAttendanceStatus(), appointmentAttend.getAppointmentDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.buildError(CodeMsg.DATA_QUERY_ERROR);
        }
        return Result.buildSuccess(appointmentList);
    }


}
