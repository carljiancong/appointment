package com.harmonycloud.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class AppoinmentBo {
    @ApiModelProperty(name = "预约id",example = "1")
    private Integer appoinmentId;
    @ApiModelProperty(name = "病患id",example = "1")
    private Integer patientId;
    @ApiModelProperty(name = "诊所id",example = "1")
    private Integer clinicId;
    @ApiModelProperty(name = "会诊类型id",example = "1")
    private Integer encounterTypeId;
    @ApiModelProperty(name = "诊室id",example = "1")
    private Integer roomId;
    @ApiModelProperty(name = "预约日期id",example = "02-Jan-2019")
    private Date appoinmentDate;
    @ApiModelProperty(name = "预约能否到达",example = "1")
    private String status;
    @ApiModelProperty(name = "是否能到达",example = "1")
    private String attendanceStatus;
    @ApiModelProperty(name = "到达",example = "2019-02-20 11:11:39")
    private Date attendanceTime;

    public AppoinmentBo() {
    }

    public AppoinmentBo(Integer appoinmentId, Integer patientId, Integer clinicId,
                        Integer encounterTypeId, Integer roomId, Date appoinmentDate, String status,
                        String attendanceStatus, Date attendanceTime) {
        this.appoinmentId = appoinmentId;
        this.patientId = patientId;
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.roomId = roomId;
        this.appoinmentDate = appoinmentDate;
        this.status = status;
        this.attendanceStatus = attendanceStatus;
        this.attendanceTime = attendanceTime;
    }

    public Integer getId() {
        return appoinmentId;
    }

    public void setId(Integer appoinmentId) {
        this.appoinmentId = appoinmentId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Integer getEncounterTypeId() {
        return encounterTypeId;
    }

    public void setEncounterTypeId(Integer encounterTypeId) {
        this.encounterTypeId = encounterTypeId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getDate() {
        return appoinmentDate;
    }

    public void setDate(Date appoinmentDate) {
        this.appoinmentDate = appoinmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public Date getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Date attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
}
