package com.harmonycloud.bo;

import com.harmonycloud.entity.AppointmentQuota;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


public class AppointmentQuotaBo {
    private Integer appointmentQuotaId;
    private Integer clinicId;
    private Integer encounterTypeId;
    private Date appointmentDate;
    private Integer quota;
    private boolean isHoliday;

    public AppointmentQuotaBo() { }

    public AppointmentQuotaBo(Integer appointmentQuotaId, Integer clinicId, Integer encounterTypeId, Date appointmentDate, Integer quota, boolean isHoliday) {
        this.appointmentQuotaId = appointmentQuotaId;
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.appointmentDate = appointmentDate;
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

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
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
}


