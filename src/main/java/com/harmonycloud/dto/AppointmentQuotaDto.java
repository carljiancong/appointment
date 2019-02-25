package com.harmonycloud.dto;

import com.harmonycloud.bo.AppointmentQuotaBo;


import java.io.Serializable;
import java.util.List;

public class AppointmentQuotaDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer roomId;

    private List<AppointmentQuotaBo> appointmentQuotaquotabo;

    public AppointmentQuotaDto() {
    }

    public AppointmentQuotaDto(Integer roomId, List<AppointmentQuotaBo> appointmentQuotaquotabo) {
        this.roomId = roomId;
        this.appointmentQuotaquotabo = appointmentQuotaquotabo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public List<AppointmentQuotaBo> getAppointmentQuotaquotabo() {
        return appointmentQuotaquotabo;
    }

    public void setAppointmentQuotaquotabo(List<AppointmentQuotaBo> appointmentQuotaquotabo) {
        this.appointmentQuotaquotabo = appointmentQuotaquotabo;
    }
}
