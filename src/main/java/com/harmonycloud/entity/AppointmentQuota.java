package com.harmonycloud.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Appointment_quota")
public class AppointmentQuota {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer appointmentQuotaId;

    @Column(name = "clinic_id")
    private Integer clinicId;

    @Column(name = "encounter_type_id")
    private Integer encounterTypeId;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "quota")
    private Integer quota;

    @Column(name = "quota_booked")
    private Integer quotaBooked;

    public AppointmentQuota() {
    }

    public AppointmentQuota(Integer appointmentQuotaId, Integer clinicId, Integer encounterTypeId,
                            Integer roomId, LocalDate appointmentDate, Integer quota, Integer quotaBooked) {
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

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
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
