package com.harmonycloud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @author qidong
 * @date 2019/2/13
 */

@Entity
@Table(name = "appoinment")
@ApiModel
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(name = "预约id",example = "1")
    private Integer appointmentId;
    @Column(name = "patient_id")
    @ApiModelProperty(name = "病患id",example = "1")
    private Integer patientId;
    @Column(name = "clinic_id")
    @ApiModelProperty(name = "诊所id",example = "1")
    private Integer clinicId;
    @Column(name = "encounter_type_id")
    @ApiModelProperty(name = "会诊类型id",example = "1")
    private Integer encounterTypeId;
    @Column(name = "room_id")
    @ApiModelProperty(name = "诊室id",example = "1")
    private Integer roomId;
    @Column(name = "appointment_date")
    @ApiModelProperty(name = "预约日期id",example = "02-Jan-2019")
    private String appointmentDate;
    @Column(name = "status")
    @ApiModelProperty(name = "预约能否到达",example = "1")
    private String status;
    @Column(name = "attendance_status")
    @ApiModelProperty(name = "是否能到达",example = "1")
    private String attendanceStatus;
    @Column(name = "attendance_time")
    @ApiModelProperty(name = "到达",example = "2019-02-20 11:11:39")
    private Date attendanceTime;

    public Appointment(){
    }

    public Appointment(Integer patientId, Integer clinicId,
                       Integer encounterTypeId, Integer roomId, String appointmentDate, String attendanceStatus) {
        this.patientId = patientId;
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.roomId = roomId;
        this.appointmentDate = appointmentDate;
        this.attendanceStatus = attendanceStatus;
    }

    public Integer getId() {
        return appointmentId;
    }

    public void setId(Integer appointmentId) {
        this.appointmentId = appointmentId;
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

    public String getDate() {
        return appointmentDate;
    }

    public void setDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
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
