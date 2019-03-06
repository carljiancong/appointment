package com.harmonycloud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


/**
 * @author qidong
 * @String 2019/2/13
 */
@Entity
@Table(name = "Appoinment_quota")
@ApiModel
public class AppointmentQuota {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(name = "预约id", example = "1")
    private Integer appointmentQuotaId;
    @ApiModelProperty(name = "诊断id", example = "1")
    @Column(name = "clinic_id")
    private Integer clinicId;
    @Column(name = "encounter_type_id")
    @ApiModelProperty(name = "会诊类型id", example = "1")
    private Integer encounterTypeId;
    @Column(name = "room_id")
    @ApiModelProperty(name = "诊室id", example = "1")
    private Integer roomId;
    @Column(name = "appointment_date")
    @ApiModelProperty(name = "预约时间", example = "01-Jan-2019")
    private LocalDate appointmentDate;
    @Column(name = "quota")
    @ApiModelProperty(name = "人数额度", example = "10")
    private Integer quota;
    @Column(name = "quota_booked")
    @ApiModelProperty(name = "已经预定额度", example = "1")
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
