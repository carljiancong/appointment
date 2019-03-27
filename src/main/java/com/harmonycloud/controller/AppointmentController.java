package com.harmonycloud.controller;

import com.google.common.collect.Lists;
import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.entity.Appointment;
import com.harmonycloud.entity.AppointmentQuota;
import com.harmonycloud.dto.AppointmentAttend;
import com.harmonycloud.bo.AppointmentBo;
import com.harmonycloud.dto.AppointmentByMonth;
import com.harmonycloud.enums.ErrorMsgEnum;
import com.harmonycloud.exception.AppointmentException;
import com.harmonycloud.result.CimsResponseWrapper;
import com.harmonycloud.service.AppointmentQuotaService;
import com.harmonycloud.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.servicecomb.saga.omega.context.annotations.SagaStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value = "Appointment")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentQuotaService appointmentQuotaService;

    /**
     * get patient appointment list
     *
     * @param patientId patientId
     * @return CimsResponseWrapper
     * @throws Exception
     */
    @GetMapping("/appointmentHistory")
    @ApiOperation(value = "patient appointment list", response = Appointment.class)
    @ApiImplicitParam(name = "patientId", value = "patientId", paramType = "query", dataType = "Integer")
    public CimsResponseWrapper<List> getAppointmentHistory(@RequestParam("patientId") Integer patientId) throws Exception {
        if (patientId == null || patientId <= 0) {
            throw new AppointmentException(ErrorMsgEnum.PARAMETER_ERROR.getMessage());
        }
        List<Appointment> appointmentList = appointmentService.getAppointmentHistory(patientId);

        return new CimsResponseWrapper<>(true, null, appointmentList);
    }

    /**
     * get the appointment quota in this month
     *
     * @param appointmentByMonth model
     * @return CimsResponseWrapper
     * @throws Exception
     */
    @ApiOperation(value = "quota list", response = AppointmentQuota.class)
    @ApiImplicitParam(name = "appointmentByMonth", value = "appointmentByMonth", required = true, dataType = "AppointmentByMonth")
    @PostMapping("/quotaList")
    public CimsResponseWrapper<Map> getQuotaList(@RequestBody AppointmentByMonth appointmentByMonth) throws Exception {
        if (appointmentByMonth.getClinicId() == null || appointmentByMonth.getEncounterTypeId() == null) {
            throw new AppointmentException(ErrorMsgEnum.PARAMETER_ERROR.getMessage());
        }

        List<AppointmentQuotaBo> appointmentQuotaBoList = appointmentQuotaService.getAppointmentQuotaList(appointmentByMonth);
        //group by roomId
        Map<Integer, List<AppointmentQuotaBo>> map = new HashMap<>();
        appointmentQuotaBoList.forEach(appointmentQuotaBo -> {
            if (!map.containsKey(appointmentQuotaBo.getRoomId())) {
                map.put(appointmentQuotaBo.getRoomId(), Lists.newArrayList());
            }
            map.get(appointmentQuotaBo.getRoomId()).add(appointmentQuotaBo);
        });

        return new CimsResponseWrapper<>(true, null, map);
    }

    /**
     * get appointment list by special day
     *
     * @param appointmentAttend model
     * @return CimsResponseWrapper
     * @throws Exception
     */
    @ApiOperation(value = "appointment list by special day", response = Appointment.class)
    @ApiImplicitParam(name = "appointmentAttend", value = "appointment attend", dataType = "AppointmentAttend")
    @PostMapping("/appointmentList")
    public CimsResponseWrapper<List> getAppointmentList(@RequestBody AppointmentAttend appointmentAttend) throws Exception {
        if (appointmentAttend.getAppointmentDate() == null || appointmentAttend.getAttendanceStatus() == null
                || appointmentAttend.getRoomId() == null) {
            throw new AppointmentException(ErrorMsgEnum.PARAMETER_ERROR.getMessage());
        }
        List<Appointment> appointmentList = appointmentService.getAppointmentList(appointmentAttend);
        return new CimsResponseWrapper<>(true, null, appointmentList);
    }

    /**
     * book appointment
     *
     * @param appointmentBo model
     * @return CimsResponseWrapper
     * @throws Exception
     */
    @PostMapping("/book")
    @ApiOperation(value = "Book Appointment")
    @ApiImplicitParam(name = "appointmentBo", value = "appointmentBo", dataType = "AppointmentBo")
    public CimsResponseWrapper<Appointment> bookAppointment(@RequestBody AppointmentBo appointmentBo) throws Exception {
        if (appointmentBo == null || appointmentBo.getClinicId() <= 0 || appointmentBo.getPatientId() <= 0 || appointmentBo.getRoomId() <= 0
                || appointmentBo.getEncounterTypeId() <= 0) {
            throw new AppointmentException(ErrorMsgEnum.PARAMETER_ERROR.getMessage());
        }
        Appointment appointment = appointmentService.bookAppointment(appointmentBo);

        return new CimsResponseWrapper<>(true, null, appointment);
    }

    /**
     * whether the day is fully booked and whether the appointment is duplicated
     *
     * @param patientId patientId
     * @param typeId    typeId
     * @param roomId    roomId
     * @param clinicId  clinicId
     * @param date      appointmentDate
     * @return CimsResponseWrapper
     * @throws Exception
     */
    @GetMapping("/isduplicated")
    @ApiOperation(value = "isduplicated", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patientId", value = "patientId", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "encounterTypeId", value = "encounterTypeId", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "roomId", value = "roomId", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "clinicId", value = "clinicId", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "appointmentDate", value = "appointmentDate", paramType = "query", dataType = "String")
    })
    public CimsResponseWrapper<String> isDuplicated(@RequestParam("patientId") Integer patientId,
                                                    @RequestParam("encounterTypeId") Integer typeId,
                                                    @RequestParam("roomId") Integer roomId,
                                                    @RequestParam("clinicId") Integer clinicId,
                                                    @RequestParam("appointmentDate") Date date) throws Exception {
        if (patientId <= 0 || typeId <= 0 || roomId <= 0 || clinicId <= 0 || date == null) {
            throw new AppointmentException(ErrorMsgEnum.PARAMETER_ERROR.getMessage());
        }

        appointmentService.isDuplicated(clinicId, patientId, typeId, roomId, date);
        return new CimsResponseWrapper<>(true, null, null);
    }

    /**
     * mark the appintment status attend
     *
     * @param id appointmentid
     * @return CimsResponseWrapper
     * @throws Exception
     */
    @GetMapping("/attend")
    @ApiOperation(value = "Mark The Attendance", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "AppointmentId", paramType = "query", dataType = "Integer")
    @Transactional(rollbackFor = Throwable.class)
//    @SagaStart
    public CimsResponseWrapper<String> markAttendance(@RequestParam("id") Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new AppointmentException(ErrorMsgEnum.PARAMETER_ERROR.getMessage());
        }
        appointmentService.markAttendance(id);
        return new CimsResponseWrapper<>(true, null, "Mark attend success");
    }
}
