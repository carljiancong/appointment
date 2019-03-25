package com.harmonycloud.service;

import com.harmonycloud.bo.UserPrincipal;
import com.harmonycloud.config.EncounterConfigurationProperties;
import com.harmonycloud.entity.Appointment;
import com.harmonycloud.dto.Encounter;
import com.harmonycloud.enums.ErrorMsgEnum;
import com.harmonycloud.exception.AppointmentException;
import com.harmonycloud.repository.AppointmentRepository;
import com.harmonycloud.dto.AppointmentAttend;
import com.harmonycloud.bo.AppointmentBo;
import com.harmonycloud.util.LogUtil;
import org.apache.servicecomb.saga.omega.context.annotations.SagaStart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private HttpServletRequest request;


    /**
     * get patient appointment history
     *
     * @param patientId patientId
     * @return List
     * @throws Exception
     */
    public List<Appointment> getAppointmentHistory(Integer patientId) throws Exception {
        String msg = LogUtil.getRequest(request) + ", information='";

        List<Appointment> appointmentList = appointmentRepository.findByPatientId(patientId);
        if (CollectionUtils.isEmpty(appointmentList)) {
            logger.info(msg + "The patient had no appointment history '");
            return null;
        }
        return appointmentList;
    }

    /**
     * book appointment
     *
     * @param appointmentBo model
     * @return Appointment
     */
    public Appointment bookAppointment(AppointmentBo appointmentBo) throws Exception {
        Long time = System.currentTimeMillis();
        String msg = LogUtil.getRequest(request) + ", information='";

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Integer clinicId = appointmentBo.getClinicId();
        Integer typeId = appointmentBo.getEncounterTypeId();
        Integer roomId = appointmentBo.getRoomId();

        Appointment appointment = new Appointment(appointmentBo.getPatientId(), clinicId, typeId, roomId, appointmentBo.getDate(), "Not Attend",
                appointmentBo.getPatientDoc(), appointmentBo.getPatientName(), appointmentBo.getPatientSex(), appointmentBo.getEncounterTypeName(),
                appointmentBo.getRoomName(), appointmentBo.getClinicName());
        //save appointment
        if (appointmentRepository.save(appointment).getAppointmentId() <= 0) {
            throw new AppointmentException(ErrorMsgEnum.BOOK_ERROR.getMessage());
        }
        //update quota
        appointmentQuotaService.updateAppointmentQuotaList(sdf.format(appointment.getAppointmentDate()), clinicId, typeId, roomId);
        logger.info(msg + "book success :{}'", appointment);
        System.out.println(System.currentTimeMillis() - time);
        logger.info(msg + "running time :", (System.currentTimeMillis() - time));
        return appointment;

    }

    /**
     * whether  the appointment is duplicated
     *
     * @param clinicId  clinicId
     * @param patientId patientId
     * @param typeId    encounterTypeId
     * @param roomId    roomId
     * @param date      appointmentDate
     */
    public void isDuplicated(Integer clinicId, Integer patientId, Integer typeId, Integer roomId, Date date) throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Set<String> holidayDateSet = holidayService.getHolidayDate();
        if (holidayDateSet.contains(sdf.format(date))) {
            throw new AppointmentException(ErrorMsgEnum.HOLIDAY.getMessage());
        }

        Boolean isfull = appointmentQuotaService.isFull(sdf.format(date), clinicId, typeId, roomId);

        if (!isfull) {
            List<Appointment> appointmentList = appointmentRepository.findByPatientIdAndEncounterTypeIdAndRoomIdAndAttendanceStatus(patientId, typeId, roomId, "Not Attend");
            if (appointmentList.size() != 0) {
                Date nowtime = sdf.parse(sdf.format(new Date()));
                for (int i = 0; i < appointmentList.size(); i++) {
                    Date appointmentTime = appointmentList.get(i).getAppointmentDate();
                    int flag = appointmentTime.compareTo(nowtime);
                    if (flag >= 0) {
                        throw new AppointmentException(ErrorMsgEnum.DUPLICATED_BOOKING.getMessage());
                    }
                }
            }

        } else {
            throw new AppointmentException(ErrorMsgEnum.FUll_BOOKING.getMessage());
        }
    }

    /**
     * mark appointment attend
     *
     * @param appointmentId appointmentId
     * @throws Exception
     */

    public void markAttendance(Integer appointmentId) throws Exception {
        String msg = LogUtil.getRequest(request) + ", information='";
        UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
        //update appointment
        Date date = new Date();
        appointment.update("Attend");
        appointment.setAttendanceTime(date);
        appointmentRepository.save(appointment);
        logger.info(msg + "appointment status update success '");

        //create encounter
        Encounter encounter = new Encounter(appointment.getPatientId(), appointment.getEncounterTypeId(), appointment.getClinicId(),
                appointment.getRoomId(), date, appointmentId);

        if (!syncService.save(config.getEncounterUri(), userDetails.getToken(), encounter).isSuccess()) {
            logger.info(msg + "create encounter error '");
            throw new AppointmentException(ErrorMsgEnum.ATTEND_ERROR.getMessage());
        }
        logger.info(msg + "mark attend success '");

    }

    /**
     * get appointment list by special day
     *
     * @param appointmentAttend model
     * @return List
     */
    public List<Appointment> getAppointmentList(AppointmentAttend appointmentAttend) {
        List<Appointment> appointmentList = new ArrayList<>();

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

        return appointmentList;
    }
}
