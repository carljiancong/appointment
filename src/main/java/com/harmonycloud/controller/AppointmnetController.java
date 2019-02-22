package com.harmonycloud.controller;

import com.harmonycloud.bo.AppointmentAttend;
import com.harmonycloud.bo.AppointmentBo;
import com.harmonycloud.bo.AppointmentByMonth;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = {"Appointment"})
@RestController
public class AppointmnetController {

    @Resource
    AppointmentService appointmentService;

    @ApiOperation(value = "patient appointment list")
    @ApiImplicitParam(name = "patientId", value = "patientId", required = true, dataType = "Integer")
    @PostMapping("/appointmentList")
    public Result getAppointmentHistory(@RequestBody Integer patientId) {
        if (patientId <= 0) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getAppointmentHistory(patientId);
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

    @ApiOperation(value = "appointment list by special day")
    @ApiImplicitParam(name = "AppointmentAttend",value = "appointment attend",dataType = "AppointmentAttend")
    @PostMapping("/appointmentlist")
    public Result getAppointmentList(@RequestBody AppointmentAttend appointmentAttend)  {
        if (appointmentAttend.getAppointmentDate() == null || appointmentAttend.getAttendanceStatus() == null
                             || appointmentAttend.getRoomId() == null) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getAppointmentList(appointmentAttend);
    }

    @PostMapping("/book")
    @ApiOperation(value = "Book Appointment")
    @ApiImplicitParam(name = "appointmentBo", value = "预约信息", dataType = "AppointmentBo")
    public Result bookAppointment(@RequestBody AppointmentBo appointmentBo) {
        return appointmentService.bookAppointment(appointmentBo);
    }

    @GetMapping("/isduplicated")
    @ApiOperation(value = "isduplicated", httpMethod = "GET")
    @ApiImplicitParam()
    public Result isDuplicated(@RequestParam("patientId") Integer patientId,
                               @RequestParam("encounterTypeId") Integer typeId,
                               @RequestParam("roomId") Integer roomId) {
        return appointmentService.isDuplicated(patientId, typeId, roomId);
    }

    @GetMapping("/attend")
    @ApiOperation(value = "Mark The Attendance", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "AppointmentId", paramType = "param", dataType = "Integer")
    public Result bookAppointment(@RequestParam("id") Integer id) {
        return appointmentService.markAttendence(id);
    }

}
