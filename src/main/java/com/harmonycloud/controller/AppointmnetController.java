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

@Api(value = "预约controller", tags = {"预约操作接口"})
@RestController
public class AppointmnetController {

    @Resource
    AppointmentService appointmentService;

    @ApiOperation(value = "获取某一个病人的所有预约")
    @ApiImplicitParam(name = "patientId", value = "病人id", required = true, dataType = "Integer")
    @GetMapping("/appointmentList")
    public Result getAppointmentList(@RequestBody Integer patientId) {
        if (patientId <= 0) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getAppointmentList(patientId);
    }

    @ApiOperation(value = "获取某一个月的预约额度列表")
    @ApiImplicitParam(name = "appointmentByMonth", value = "月份", required = true, dataType = "AppointmentByMonth")
    @GetMapping("/quotaList")
    public Result getQuotaList(@RequestBody AppointmentByMonth appointmentByMonth) {
        if (appointmentByMonth.getClinicId() == null || appointmentByMonth.getEncounterTypeId() == null ||
            appointmentByMonth.getRoomId() == null) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getQuotaList(appointmentByMonth);
    }

    @ApiOperation(value = "Book Appointment")
    @ApiImplicitParam(name = "appointmentBo",value = "预约信息",dataType = "AppointmentBo")
    @PostMapping("/book")
    public Result bookAppointment(@RequestBody AppointmentBo appointmentBo){
        return appointmentService.bookAppointment(appointmentBo);
    }


    @ApiOperation(value = "获取病患出席列表")
    @ApiImplicitParam(name = "AppointmentAttend",value = "查询出席信息",dataType = "AppointmentAttend")
    @GetMapping("/attendList")
    public Result getAttendList(@RequestBody AppointmentAttend appointmentAttend)  {
        if (appointmentAttend.getAppointmentDate() == null || appointmentAttend.getAttendanceStatus() == null
                             || appointmentAttend.getRoomId() == null) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getAttendList(appointmentAttend);
    }
}
