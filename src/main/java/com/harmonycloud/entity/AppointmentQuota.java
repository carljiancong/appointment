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
@Table(name = "Appoinment_quota")
public class AppointmentQuota {
    @Id
    private Integer appoinmentQuotaId;
    @Column(name = "clinic_id")
    private Integer clinicId;
    @Column(name = "encounter_type_id")
    private Integer encounterTypeId;
    @Column(name = "room_id")
    private Integer roomId;
    @Column(name = "appoinment_date")
    private Date appoinmentDate;
    @Column(name = "quota")
    private Integer quota;
    @Column(name = "quota_booked")
    private Integer quotaBooked;

    public AppointmentQuota() {
    }

    public AppointmentQuota(Integer appoinmentQuotaId, Integer clinicId, Integer encounterTypeId,
                            Integer roomId, Date appoinmentDate, Integer quota, Integer quotaBooked) {
        this.appoinmentQuotaId = appoinmentQuotaId;
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.roomId = roomId;
        this.appoinmentDate = appoinmentDate;
        this.quota = quota;
        this.quotaBooked = quotaBooked;
    }

    public Integer getId() {
        return appoinmentQuotaId;
    }

    public void setId(Integer appoinmentQuotaId) {
        this.appoinmentQuotaId = appoinmentQuotaId;
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
        return appoinmentDate;
    }

    public void setDate(Date appoinmentDate) {
        this.appoinmentDate = appoinmentDate;
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
