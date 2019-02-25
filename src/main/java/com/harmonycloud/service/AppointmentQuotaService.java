package com.harmonycloud.service;

import com.harmonycloud.vo.AppointmentByMonth;
import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.dto.AppointmentQuotaDto;
import com.harmonycloud.entity.AppointmentQuota;
import com.harmonycloud.repository.AppointmentQuotaRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class AppointmentQuotaService {

    @Resource
    private AppointmentQuotaRepository appointmentQuotaRepository;
    @Resource
    private HolidayService holidayService;

    public Result getAppointmentQuotaList(AppointmentByMonth appointmentByMonth) {

        //获取该年月下的所有假期，并放入set集合中
        Set<String> holidayDateSet = holidayService.getHolidayDate();
        Integer[] roomId = appointmentByMonth.getRoomId();
        List<AppointmentQuotaBo> appointmentQuotaBoList = new ArrayList<AppointmentQuotaBo>();
        List<AppointmentQuotaDto> appointmentQuotaDtoList = new ArrayList<AppointmentQuotaDto>();
        try {
            List<AppointmentQuota> appointmentQuotaList = appointmentQuotaRepository.findByClinicIdAndEncounterTypeIdAndAppointmentDateContaining(
                    appointmentByMonth.getClinicId(), appointmentByMonth.getEncounterTypeId(),
                    appointmentByMonth.getMonthYear());
            //判断这一天是否是假期，存入AppointmentQuotaBo中
            for (int i = 0; i < appointmentQuotaList.size(); i++) {
                if (holidayDateSet.contains(appointmentQuotaList.get(i).getAppointmentDate())) {
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
                List<AppointmentQuotaBo> appointmentQuotaBoList1 = new ArrayList<AppointmentQuotaBo>();
                for (int j = 0; j < appointmentQuotaBoList.size(); j++) {
                    if (appointmentQuotaList.get(j).getRoomId() == roomId[i]) {
                        appointmentQuotaBoList1.add(appointmentQuotaBoList.get(j));
                    }
                }
                AppointmentQuotaDto appointmentQuotaDto = new AppointmentQuotaDto(roomId[i], appointmentQuotaBoList1);
                appointmentQuotaDtoList.add(appointmentQuotaDto);
            }
            return Result.buildSuccess(appointmentQuotaDtoList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
    }
}
