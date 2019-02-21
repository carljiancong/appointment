package com.harmonycloud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author qidong
 * @date 2019/2/13
 */
@Entity
@Table(name = "encounter")
public class Encounter {
    @Id
    private Integer encounterId;
    @Column(name = "patient_id")
    private Integer patientId;
    @Column(name = "encounter_type_id")
    private Integer encounterTypeId;
    @Column(name = "clinic_id")
    private Integer clinicId;
    @Column(name = "room_id")
    private Integer roomId;
    @Column(name = "date_time")
    private Date dateTime;
    @Column(name = "appointment_id")
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

    public Encounter(Integer encounterId, Integer patientId, Integer encounterTypeId,
                     Integer clinicId, Integer roomId, Date dateTime, Integer appointmentId) {
        this.encounterId = encounterId;
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
}
