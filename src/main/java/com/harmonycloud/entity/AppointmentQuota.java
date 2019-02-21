package com.harmonycloud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author qidong
 * @String 2019/2/13
 */
@Entity
@Table(name = "Appoinment_quota")
public class AppointmentQuota {
    @Id
    private Integer appointmentQuotaId;
    @Column(name = "clinic_id")
    private Integer clinicId;
    @Column(name = "encounter_type_id")
    private Integer encounterTypeId;
    @Column(name = "room_id")
    private Integer roomId;
    @Column(name = "appointment_date")
    private String appointmentDate;
    @Column(name = "quota")
    private Integer quota;
    @Column(name = "quota_booked")
    private Integer quotaBooked;

    public AppointmentQuota() {
    }

    public AppointmentQuota(Integer appointmentQuotaId, Integer clinicId, Integer encounterTypeId,
                            Integer roomId, String appointmentDate, Integer quota, Integer quotaBooked) {
        this.appointmentQuotaId = appointmentQuotaId;
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.roomId = roomId;
        this.appointmentDate = appointmentDate;
        this.quota = quota;
        this.quotaBooked = quotaBooked;
    }

    public Integer getAppointmentQuotaId() {
        return appointmentQuotaId;
    }

    public void setAppointmentQuotaId(Integer appointmentQuotaId) {
        this.appointmentQuotaId = appointmentQuotaId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
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

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getQuotaBooked() {
        return quotaBooked;
    }

    public void setQuotaBooked(Integer quotaBooked) {
        this.quotaBooked = quotaBooked;
    }
}
