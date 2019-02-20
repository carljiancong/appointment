package com.harmonycloud.Controller;

import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "预约controller", tags = {"预约操作接口"})
@RestController
public class AppointmnetController {

    @Resource
    AppointmentService appointmentService;

    @ApiOperation(value = "获取某一个病人的所有预约")
    @ApiImplicitParam(name = "patientId", value = "病人id", required = true, dataType = "Integer")
    @PostMapping("/appointmentList")
    public Result getAppointmentList(@RequestBody Integer patientId) {
        if (patientId <= 0) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getAppointmentList(patientId);
    }

    @ApiOperation(value = "获取某一个月的预约额度列表")
    @ApiImplicitParam(name = "param", value = "月份", required = true, dataType = "String")
    @PostMapping("/quotaList")
    public Result getQuotaList(@RequestBody String param) {
        if (param == null) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return null;
    }

}
