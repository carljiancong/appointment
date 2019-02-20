package com.harmonycloud.entity;

import java.util.Date;

/**
 * @author qidong
 * @date 2019/2/13
 */
public class Appoinment {
    private Integer appoinmentId;
    private Integer patientId;
    private Integer clinicId;
    private Integer encounterTypeId;
    private Integer roomId;
    private Date appoinmentDate;
    private String status;
    private String attendanceStatus;
    private Date attendanceTime;

    public Appoinment() {
    }

    public Appoinment(Integer appoinmentId, Integer patientId, Integer clinicId,
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
