package com.harmonycloud.vo;


import java.util.Date;

public class AppointmentByMonth {
    private Integer clinicId;
    private Integer encounterTypeId;
    private String monthYear;
    private Integer[] roomId;

    public AppointmentByMonth(Integer clinicId, Integer encounterTypeId, String monthYear) {
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
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

    public Integer[] getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer[] roomId) {
        this.roomId = roomId;
    }
}
