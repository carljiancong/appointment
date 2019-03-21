package com.harmonycloud.entity;

import com.harmonycloud.enums.ErrorMsgEnum;
import com.harmonycloud.exception.AppointmentException;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appoinment")
@ApiModel
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer appointmentId;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "patient_doc")
    private String patientDoc;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "patient_sex")
    private String patientSex;

    @Column(name = "clinic_id")
    private Integer clinicId;

    @Column(name = "clinic_name")
    private String clinicName;

    @Column(name = "encounter_type_id")
    private Integer encounterTypeId;

    @Column(name = "encounter_type_name")
    private String encounterTypeName;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "status")
    private String status;

    @Column(name = "attendance_status")
    private String attendanceStatus;

    @Column(name = "attendance_time")
    private Date attendanceTime;

    public Appointment() {
    }

    public Appointment(Integer patientId, Integer clinicId,
                       Integer encounterTypeId, Integer roomId, Date appointmentDate, String attendanceStatus,
                       String patientDoc, String patientName, String patientSex, String encounterTypeName, String roomName, String clinicName) {
        this.patientId = patientId;
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.roomId = roomId;
        this.appointmentDate = appointmentDate;
        this.attendanceStatus = attendanceStatus;
        this.patientDoc = patientDoc;
        this.patientSex = patientSex;
        this.patientName = patientName;
        this.encounterTypeName = encounterTypeName;
        this.roomName = roomName;
        this.clinicName = clinicName;
    }

    public void update(String appointmentStatus) throws Exception {
        if (appointmentStatus.equals("Attend")) {
            if (this.attendanceStatus.equals("Not Attend")) {
                this.attendanceStatus = appointmentStatus;
            } else {
                throw new AppointmentException(ErrorMsgEnum.ILLEGAL.getMessage());
            }
        }
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Date getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Date attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getPatientDoc() {
        return patientDoc;
    }

    public void setPatientDoc(String patientDoc) {
        this.patientDoc = patientDoc;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getEncounterTypeName() {
        return encounterTypeName;
    }

    public void setEncounterTypeName(String encounterTypeName) {
        this.encounterTypeName = encounterTypeName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
}
