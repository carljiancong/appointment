package com.harmonycloud.service;

import com.harmonycloud.dto.AppoinmentDto;
import com.harmonycloud.entity.AppointmentQuota;
import com.harmonycloud.repository.AppointmentRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppointmentService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
//    private JwtUtil jwtUtil = new JwtUtil();

    @Resource
    private AppointmentRepository appointmentRepository;

    public List<AppointmentQuota> listquota(int month, int clinicid, int encountertypeid) {
        try{
            String strParaMonthn = "Jan_Feb_Mar_Apr_May_Jun_Jul_Aug_Sep_Oct_Nov_Dec";
            String[] strSubMonth = strParaMonthn.split("_".toCharArray().toString());
            String strReturn = strSubMonth[month - 1];


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public Result getAppointmentList(Integer patientId) {
        List<AppoinmentDto> appoinmentDtoList = null;
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
}
