package com.harmonycloud.service;

import com.harmonycloud.dto.AppointmentByMonth;
import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.entity.AppointmentQuota;
import com.harmonycloud.repository.AppointmentQuotaRepository;
import com.harmonycloud.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class AppointmentQuotaService {

    private Logger logger = LoggerFactory.getLogger(AppointmentService.class);


    @Autowired
    private AppointmentQuotaRepository appointmentQuotaRepository;

    @Autowired
    private HolidayService holidayService;
    @Autowired
    HttpServletRequest request;


    /**
     * get appointment quota in a month
     *
     * @param appointmentByMonth model
     * @return
     */
    public List<AppointmentQuotaBo> getAppointmentQuotaList(AppointmentByMonth appointmentByMonth) throws Exception {
        //get the holiday to set
        Set<String> holidayDateSet = holidayService.getHolidayDate();

        List<AppointmentQuotaBo> appointmentQuotaBoList = new ArrayList<AppointmentQuotaBo>();

        //get appointment quota list in a month
        List<AppointmentQuota> appointmentQuotaList = appointmentQuotaRepository.findByQuotalist(
                appointmentByMonth.getClinicId(), appointmentByMonth.getEncounterTypeId(), appointmentByMonth.getMonthYear());
        //if the day is a holiday，save  AppointmentQuotaBo
        appointmentQuotaList.forEach(appointmentQuota -> {
            if (holidayDateSet.contains(appointmentQuota.getAppointmentDate().toString())) {
                AppointmentQuotaBo appointmentQuotaBo = new AppointmentQuotaBo(appointmentQuota.getAppointmentQuotaId(),
                        appointmentQuota.getClinicId(), appointmentQuota.getEncounterTypeId(), appointmentQuota.getAppointmentDate(),
                        appointmentQuota.getRoomId(), appointmentQuota.getQuota(), true);
                appointmentQuotaBoList.add(appointmentQuotaBo);
            } else {
                AppointmentQuotaBo appointmentQuotaBo = new AppointmentQuotaBo(appointmentQuota.getAppointmentQuotaId(),
                        appointmentQuota.getClinicId(), appointmentQuota.getEncounterTypeId(), appointmentQuota.getAppointmentDate(),
                        appointmentQuota.getRoomId(), appointmentQuota.getQuota(), false);
                appointmentQuotaBoList.add(appointmentQuotaBo);
            }
        });
        return appointmentQuotaBoList;

    }

    /**
     * book appointment success , update appointment quota
     *
     * @param appointmentDate appointment date
     * @param clinicId        clinicId
     * @param encouterTypeId  encounterTypeId
     * @param roomID          roomId
     */

    public void updateAppointmentQuotaList(String appointmentDate, Integer clinicId, Integer encouterTypeId, Integer roomID) throws Exception {
        String msg = LogUtil.getRequest(request) + ", information='";

        AppointmentQuota appointmentQuota = appointmentQuotaRepository.findByappointmentDate(clinicId, encouterTypeId, roomID, appointmentDate);
        int quota = appointmentQuota.getQuota() - 1;
        int booked = appointmentQuota.getQuotaBooked() + 1;
        appointmentQuota.setQuota(quota);
        appointmentQuota.setQuotaBooked(booked);
        appointmentQuotaRepository.save(appointmentQuota);
        logger.info(msg + "quota update success '");
    }

    /**
     * whether the day is fully booked, if full return true
     *
     * @param appointmentDate appointmentDate
     * @param clinicId        clinicId
     * @param encounterTypeId encounterTypeId
     * @param roomID          roomId
     * @return
     */
    public Boolean isFull(String appointmentDate, Integer clinicId, Integer encounterTypeId, Integer roomID) {
        AppointmentQuota appointmentQuota = appointmentQuotaRepository.findByappointmentDate(clinicId, encounterTypeId, roomID, appointmentDate);
        int quota = appointmentQuota.getQuota();
        if (quota > 0) {
            return false;
        } else {
            return true;
        }
    }
}
