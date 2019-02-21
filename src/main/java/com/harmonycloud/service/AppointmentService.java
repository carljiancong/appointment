package com.harmonycloud.service;

import com.harmonycloud.bo.AppoinmentBo;
import com.harmonycloud.bo.AppointmentByMonth;
import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.dto.AppointmentQuotaDto;
import com.harmonycloud.entity.AppointmentQuota;
import com.harmonycloud.entity.Holiday;
import com.harmonycloud.repository.AppointmentQuotaRepository;
import com.harmonycloud.repository.AppointmentRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
//    private JwtUtil jwtUtil = new JwtUtil();

    @Resource
    private AppointmentRepository appointmentRepository;

    @Resource
    private AppointmentQuotaService appointmentQuotaService;

    @Resource
    private HolidayService holidayService;

    public Result getQuotaList(AppointmentByMonth appointmentByMonth) {
        try{
            //获取该年月下的所有假期，并放入set集合中
            List<String> holidayDateList = holidayService.getHolidayDate(appointmentByMonth.getMonthYear());
            Set<String> holidayDateSet = new HashSet<>(holidayDateList);

            //获取该年月下的所有预约额度
            List<AppointmentQuotaBo> appointmentQuotaBoList = appointmentQuotaService.getAppointmentQuotaBoList(appointmentByMonth);

            List<AppointmentQuotaDto> appointmentQuotaDtoList = new ArrayList<>();
            for (AppointmentQuotaBo aqb: appointmentQuotaBoList) {
                AppointmentQuotaDto appointmentQuotaDto = new AppointmentQuotaDto();
                appointmentQuotaDto.setAppointmentQuotaBo(aqb);
                if (holidayDateSet.contains(aqb.getDate())) {
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
        List<AppoinmentBo> appoinmentDtoList = null;
        try {
            appoinmentDtoList = appointmentRepository.findByPatientId(patientId);
            if (appoinmentDtoList.size() == 0 || appoinmentDtoList == null) {
                return Result.buildError(CodeMsg.PATIENT_NOT_EXIST);
            }
        } catch(Exception e) {
            logger.info(e.getMessage());
            return Result.buildError(CodeMsg.DATA_QUERY_ERROR);
        }
        return Result.buildSuccess(appoinmentDtoList);
    }

    public List<Holiday> getHoliday() {
        return holidayService.getHoliday();
    }
}
