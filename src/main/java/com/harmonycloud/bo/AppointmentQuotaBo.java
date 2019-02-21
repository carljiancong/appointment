package com.harmonycloud.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author qidong
 * @String 2019/2/21
 */
@ApiModel
public class AppointmentQuotaBo {
    @ApiModelProperty(name = "预约id", example = "1")
    private Integer appointmentQuotaId;
    @ApiModelProperty(name = "诊断id", example = "1")
    private Integer clinicId;
    @ApiModelProperty(name = "会诊类型id", example = "1")
    private Integer encounterTypeId;
    @ApiModelProperty(name = "诊室id", example = "1")
    private Integer roomId;
    @ApiModelProperty(name = "预约时间", example = "01-Jan-2019")
    private String appointmentDate;
    @ApiModelProperty(name = "人数额度", example = "10")
    private Integer quota;
    @ApiModelProperty(name = "已经预定额度", example = "1")
    private Integer quotaBooked;

    public AppointmentQuotaBo() {
    }

    public AppointmentQuotaBo(Integer appointmentQuotaId, Integer clinicId, Integer encounterTypeId,
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
