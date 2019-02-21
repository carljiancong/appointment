package com.harmonycloud.bo;

/**
 * @author qidong
 * @date 2019/2/20
 */
public class AppointmentByMonth {
    private Integer clinicId;
    private Integer encounterTypeId;
    private Integer roomId;
    private String monthYear;

    public AppointmentByMonth(Integer clinicId, Integer encounterTypeId, Integer roomId,String monthYear) {
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.roomId = roomId;
        this.monthYear = monthYear;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public AppointmentByMonth() {
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
}
