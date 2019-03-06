package com.harmonycloud.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

public class Encounter {
    private Integer encounterId;
    private Integer patientId;
    private Integer encounterTypeId;
    private Integer clinicId;
    private Integer roomId;
    private Date dateTime;
    private Integer appointmentId;

    public Encounter() {
    }

    public Integer getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Integer encounterId) {
        this.encounterId = encounterId;
    }

    public Integer getAppinmentId() {
        return appointmentId;
    }

    public void setAppinmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Encounter(Integer patientId, Integer encounterTypeId,
                     Integer clinicId, Integer roomId, Date dateTime, Integer appointmentId) {
        this.patientId = patientId;
        this.encounterTypeId = encounterTypeId;
        this.clinicId = clinicId;
        this.roomId = roomId;
        this.dateTime = dateTime;
        this.appointmentId = appointmentId;
    }

    public Integer getId() {
        return encounterId;
    }

    public void setId(Integer encounterId) {
        this.encounterId = encounterId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getEncounterTypeId() {
        return encounterTypeId;
    }

    public void setEncounterTypeId(Integer encounterTypeId) {
        this.encounterTypeId = encounterTypeId;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }
}
