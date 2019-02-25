package com.harmonycloud.controller;

import com.harmonycloud.entity.Appointment;
import com.harmonycloud.entity.AppointmentQuota;
import com.harmonycloud.vo.AppointmentAttend;
import com.harmonycloud.vo.AppointmentVo;
import com.harmonycloud.vo.AppointmentByMonth;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.service.AppointmentQuotaService;
import com.harmonycloud.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "Appointment")
@RestController
public class AppointmentController {

    @Resource
    AppointmentService appointmentService;

    @Resource
    AppointmentQuotaService appointmentQuotaService;


    @GetMapping("/appointmentHistory")
    @ApiOperation(value = "patient appointment list",response = Appointment.class)
    @ApiImplicitParam(name = "patientId", value = "patientId", required = true, paramType = "query",dataType = "Integer")
    public Result getAppointmentHistory(@RequestParam("patientId") Integer patientId) {
        if (patientId <= 0) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getAppointmentHistory(patientId);
    }

    @ApiOperation(value = "quota list",response = AppointmentQuota.class)
    @ApiImplicitParam(name = "appointmentByMonth", value = "appointmentByMonth", required = true, dataType = "AppointmentByMonth")
    @PostMapping("/quotaList")
    public Result getQuotaList(@RequestBody AppointmentByMonth appointmentByMonth) {
        if (appointmentByMonth.getClinicId() == null || appointmentByMonth.getEncounterTypeId() == null) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentQuotaService.getAppointmentQuotaList(appointmentByMonth);
    }

    @ApiOperation(value = "appointment list by special day",response = Appointment.class)
    @ApiImplicitParam(name = "AppointmentAttend", value = "appointment attend", dataType = "AppointmentAttend")
    @PostMapping("/appointmentlist")
    public Result getAppointmentList(@RequestBody AppointmentAttend appointmentAttend) {
        if (appointmentAttend.getAppointmentDate() == null || appointmentAttend.getAttendanceStatus() == null
                || appointmentAttend.getRoomId() == null) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return appointmentService.getAppointmentList(appointmentAttend);
    }

    @PostMapping("/book")
    @ApiOperation(value = "Book Appointment")
    @ApiImplicitParam(name = "appointmentBo", value = "预约信息", dataType = "AppointmentBo")
    public Result bookAppointment(@RequestBody AppointmentVo appointmentvo) {
        return appointmentService.bookAppointment(appointmentvo);
    }

    @GetMapping("/isduplicated")
    @ApiOperation(value = "isduplicated", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "patientId", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "encounterTypeId", value = "encounterTypeId", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "roomId", value = "roomId", paramType = "query", dataType = "Integer")
    })
    public Result isDuplicated(@RequestParam("patientId") Integer patientId,
                               @RequestParam("encounterTypeId") Integer typeId,
                               @RequestParam("roomId") Integer roomId) {
        return appointmentService.isDuplicated(patientId, typeId, roomId);
    }

    @GetMapping("/attend")
    @ApiOperation(value = "Mark The Attendance", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "AppointmentId", paramType = "query", dataType = "Integer")
    public Result bookAppointment(@RequestParam("id") Integer id) {
        return appointmentService.markAttendence(id);
    }

}
