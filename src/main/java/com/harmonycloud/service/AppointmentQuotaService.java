package com.harmonycloud.service;

import com.harmonycloud.bo.AppointmentByMonth;
import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.repository.AppointmentQuotaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qidong
 * @date 2019/2/21
 */
@Service
public class AppointmentQuotaService {

    @Resource
    private AppointmentQuotaRepository appointmentQuotaRepository;

    public List<AppointmentQuotaBo> getAppointmentQuotaBoList(AppointmentByMonth appointmentByMonth) {
        List<AppointmentQuotaBo> appointmentQuotaBoList = null;
        try {
            appointmentQuotaBoList = appointmentQuotaRepository.findByMonth(
                    appointmentByMonth.getClinicId(),appointmentByMonth.getEncounterTypeId(),
                    appointmentByMonth.getRoomId(),appointmentByMonth.getMonthYear()
            );
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return appointmentQuotaBoList;
    }
}
