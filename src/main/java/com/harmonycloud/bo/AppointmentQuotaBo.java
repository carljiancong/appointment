package com.harmonycloud.bo;

import java.time.LocalDate;


public class AppointmentQuotaBo {

    private Integer appointmentQuotaId;

    private Integer clinicId;

    private Integer encounterTypeId;

    private LocalDate appointmentDate;

    private Integer roomId;

    private Integer quota;

    private boolean isHoliday;

    public AppointmentQuotaBo() {
    }

    public AppointmentQuotaBo(Integer appointmentQuotaId, Integer clinicId, Integer encounterTypeId, LocalDate appointmentDate, Integer roomId, Integer quota, boolean isHoliday) {
        this.appointmentQuotaId = appointmentQuotaId;
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.appointmentDate = appointmentDate;
        this.roomId = roomId;
        this.quota = quota;
        this.isHoliday = isHoliday;
    }

    public Integer getAppointmentQuotaId() {
        return appointmentQuotaId;
    }

    public void setAppointmentQuotaId(Integer appointmentQuotaId) {
        this.appointmentQuotaId = appointmentQuotaId;
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

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}


