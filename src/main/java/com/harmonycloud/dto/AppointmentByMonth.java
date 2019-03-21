package com.harmonycloud.dto;


public class AppointmentByMonth {
    private Integer clinicId;

    private Integer encounterTypeId;

    //yyyy-mm
    private String monthYear;

    public AppointmentByMonth() {
    }

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


}
