package com.harmonycloud.controller;

import com.harmonycloud.bo.AppointmentAttend;
import com.harmonycloud.bo.AppointmentBo;
import com.harmonycloud.bo.AppointmentByMonth;
import com.harmonycloud.entity.Holiday;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = {"Appointment"})
@RestController
public class AppointmnetController {

    @Resource
    AppointmentService appointmentService;

    @ApiOperation(value = "patient appointment list")
    @ApiImplicitParam(name = "patientId", value = "patientId", required = true, dataType = "Integer")
    @PostMapping("/appointmentList")
    public Result getAppointmentList(@RequestBody Integer patientId) {
        if (patientId <= 0) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getAppointmentList(patientId);
    }

    @ApiOperation(value = "quota list")
    @ApiImplicitParam(name = "appointmentByMonth", value = "appointmentByMonth", required = true, dataType = "AppointmentByMonth")
    @PostMapping("/quotaList")
    public Result getQuotaList(@RequestBody AppointmentByMonth appointmentByMonth) {
        if (appointmentByMonth.getClinicId() == null || appointmentByMonth.getEncounterTypeId() == null ||
            appointmentByMonth.getRoomId() == null) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getQuotaList(appointmentByMonth);
    }

    @ApiOperation(value = "Book Appointment")
    @ApiImplicitParam(name = "appointmentBo",value = "appointment message",dataType = "AppointmentBo")
    @PostMapping("/book")
    public Result bookAppointment(@RequestBody AppointmentBo appointmentBo){
        return appointmentService.bookAppointment(appointmentBo);
    }


    @ApiOperation(value = "attend list")
    @ApiImplicitParam(name = "AppointmentAttend",value = "appointment attend",dataType = "AppointmentAttend")
    @PostMapping("/attendList")
    public Result getAttendList(@RequestBody AppointmentAttend appointmentAttend)  {
        if (appointmentAttend.getAppointmentDate() == null || appointmentAttend.getAttendanceStatus() == null
                             || appointmentAttend.getRoomId() == null) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getAttendList(appointmentAttend);
    }
}
