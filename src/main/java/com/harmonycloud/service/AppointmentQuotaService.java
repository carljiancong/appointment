package com.harmonycloud.service;

import com.harmonycloud.dto.AppointmentByMonth;
import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.dto.AppointmentQuotaDto;
import com.harmonycloud.entity.AppointmentQuota;
import com.harmonycloud.repository.AppointmentQuotaRepository;
import com.harmonycloud.result.CimsResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class AppointmentQuotaService {

    @Autowired
    private AppointmentQuotaRepository appointmentQuotaRepository;

    @Autowired
    private HolidayService holidayService;


    /**
     * get appointment quota in a month
     *
     * @param appointmentByMonth model
     * @return
     */
    public CimsResponseWrapper<List> getAppointmentQuotaList(AppointmentByMonth appointmentByMonth) throws Exception {
        //get the holiday to set
        Set<String> holidayDateSet = holidayService.getHolidayDate();
        Integer[] roomId = appointmentByMonth.getRoomId();
        List<AppointmentQuotaBo> appointmentQuotaBoList = new ArrayList<AppointmentQuotaBo>();
        List<AppointmentQuotaDto> appointmentQuotaDtoList = new ArrayList<AppointmentQuotaDto>();

        //get appointment quota list in a month
        List<AppointmentQuota> appointmentQuotaList = appointmentQuotaRepository.findByQuotalist(
                appointmentByMonth.getClinicId(), appointmentByMonth.getEncounterTypeId(), appointmentByMonth.getMonthYear());
        //if the day is a holiday，save  AppointmentQuotaBo
        for (int i = 0; i < appointmentQuotaList.size(); i++) {
            if (holidayDateSet.contains(appointmentQuotaList.get(i).getAppointmentDate().toString())) {
                AppointmentQuotaBo appointmentQuotaBo = new AppointmentQuotaBo(appointmentQuotaList.get(i).getAppointmentQuotaId(),
                        appointmentQuotaList.get(i).getClinicId(), appointmentQuotaList.get(i).getEncounterTypeId(), appointmentQuotaList.get(i).getAppointmentDate(),
                        appointmentQuotaList.get(i).getQuota(), true);
                appointmentQuotaBoList.add(appointmentQuotaBo);
            } else {
                AppointmentQuotaBo appointmentQuotaBo = new AppointmentQuotaBo(appointmentQuotaList.get(i).getAppointmentQuotaId(),
                        appointmentQuotaList.get(i).getClinicId(), appointmentQuotaList.get(i).getEncounterTypeId(), appointmentQuotaList.get(i).getAppointmentDate(),
                        appointmentQuotaList.get(i).getQuota(), false);
                appointmentQuotaBoList.add(appointmentQuotaBo);
            }
        }
        //group by roomId
        for (int i = 0; i < roomId.length; i++) {
            List<AppointmentQuotaBo> appointmentQuotaBoListByroom = new ArrayList<AppointmentQuotaBo>();
            for (int j = 0; j < appointmentQuotaBoList.size(); j++) {
                if (appointmentQuotaList.get(j).getRoomId() == roomId[i]) {
                    appointmentQuotaBoListByroom.add(appointmentQuotaBoList.get(j));
                }
            }
            AppointmentQuotaDto appointmentQuotaDto = new AppointmentQuotaDto(roomId[i], appointmentQuotaBoListByroom);
            appointmentQuotaDtoList.add(appointmentQuotaDto);
        }




        return new CimsResponseWrapper<List>(true, null, appointmentQuotaDtoList);
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
        AppointmentQuota appointmentQuota = appointmentQuotaRepository.findByappointmentDate(clinicId, encouterTypeId, roomID, appointmentDate);
        int quota = appointmentQuota.getQuota() - 1;
        int booked = appointmentQuota.getQuotaBooked() + 1;
        appointmentQuota.setQuota(quota);
        appointmentQuota.setQuotaBooked(booked);
        appointmentQuotaRepository.save(appointmentQuota);

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
