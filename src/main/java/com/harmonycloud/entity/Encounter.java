package com.harmonycloud.entity;

import java.util.Date;

/**
 * @author qidong
 * @date 2019/2/13
 */
public class Encounter {
    private Integer encounterId;
    private Integer patientId;
    private Integer encounterTypeId;
    private Integer clinicId;
    private Integer roomId;
    private Date dateTime;

    public Encounter() {
    }

    public Encounter(Integer encounterId, Integer patientId, Integer encounterTypeId,
                     Integer clinicId, Integer roomId, Date dateTime) {
        this.encounterId = encounterId;
        this.patientId = patientId;
        this.encounterTypeId = encounterTypeId;
        this.clinicId = clinicId;
        this.roomId = roomId;
        this.dateTime = dateTime;
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
}
