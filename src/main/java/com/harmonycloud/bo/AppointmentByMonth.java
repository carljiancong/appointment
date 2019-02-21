package com.harmonycloud.bo;

/**
 * @author qidong
 * @date 2019/2/20
 */
public class AppointmentByMonth {
    private Integer clinicId;
    private Integer encounterTypeId;
    private Integer roomId;

    public AppointmentByMonth(Integer clinicId, Integer encounterTypeId, Integer roomId) {
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.roomId = roomId;
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
