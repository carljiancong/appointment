package com.harmonycloud.vo;

import java.util.Date;

public class AppointmentVo {
    private Integer patientId;
    private Integer clinicId;
    private Integer encounterTypeId;
    private Integer roomId;
    private Date date;
    private String patientName;
    private String patientDoc;
    private String patientSex;
    private String encounterTypeName;
    private String roomName;
    private String clinicName;

    public AppointmentVo() {
    }

    public AppointmentVo(Integer patientId, Integer clinicId, Integer encounterTypeId, Integer roomId, Date date, String patientName, String patientDoc, String patientSex, String encounterTypeName, String roomName, String clinicName) {
        this.patientId = patientId;
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.roomId = roomId;
        this.date = date;
        this.patientName = patientName;
        this.patientDoc = patientDoc;
        this.patientSex = patientSex;
        this.encounterTypeName = encounterTypeName;
        this.roomName = roomName;
        this.clinicName = clinicName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
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
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientDoc() {
        return patientDoc;
    }

    public void setPatientDoc(String patientDoc) {
        this.patientDoc = patientDoc;
    }

    public String getEncounterTypeName() {
        return encounterTypeName;
    }

    public void setEncounterTypeName(String encounterTypeName) {
        this.encounterTypeName = encounterTypeName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
}
