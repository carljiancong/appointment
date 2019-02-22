package com.harmonycloud.bo;

/**
 * @author qidong
 * @date 2019/2/22
 */
public class AppointmentAttend {
    private Integer roomId;
    private String attendanceStatus;
    private String appointmentDate;

    public AppointmentAttend() {
    }

    public AppointmentAttend(Integer roomId, String attendanceStatus, String appointmentDate) {
        this.roomId = roomId;
        this.attendanceStatus = attendanceStatus;
        this.appointmentDate = appointmentDate;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
