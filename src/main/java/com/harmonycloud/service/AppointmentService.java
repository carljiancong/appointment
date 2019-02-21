package com.harmonycloud.service;

import com.harmonycloud.bo.AppoinmentBo;
import com.harmonycloud.entity.AppointmentQuota;
import com.harmonycloud.entity.Holiday;
import com.harmonycloud.repository.AppointmentRepository;
import com.harmonycloud.repository.HolidayRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppointmentService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
//    private JwtUtil jwtUtil = new JwtUtil();

    @Resource
    private AppointmentRepository appointmentRepository;

    @Resource
    private HolidayRepository holidayRepository;

    @Autowired
    RedisTemplate redisTemplate;


    public Result getAppointmentList(Integer patientId) {
        List<AppoinmentBo> appoinmentDtoList = null;
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

    @Cacheable(value = "holiday", unless = "#result == null")
    public List<Holiday> getHoliday() {
        try {
            return holidayRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
