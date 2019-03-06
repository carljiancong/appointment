package com.harmonycloud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appoinment")
@ApiModel
public class Appointment {
    @Id
//    @SequenceGenerator(name = "appointmentSeq", sequenceName = "APPOINTMENT_SEQUENCE")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentSeq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ApiModelProperty(name = "预约id", example = "1")
    private Integer appointmentId;
    @Column(name = "patient_id")
    @ApiModelProperty(name = "病患id", example = "1")
    private Integer patientId;
    @Column(name = "patient_doc")
    @ApiModelProperty(name = "病患证件号码", example = "TM002")
    private String patientDoc;
    @Column(name = "patient_name")
    @ApiModelProperty(name = "病患英文名", example = "yu,vicky")
    private String patientName;
    @Column(name = "clinic_id")
    @ApiModelProperty(name = "诊所id", example = "1")
    private Integer clinicId;
    @Column(name = "clinic_name")
    @ApiModelProperty(name = "诊所名称", example = "1")
    private String clinicName;
    @Column(name = "encounter_type_id")
    @ApiModelProperty(name = "会诊类型id", example = "1")
    private Integer encounterTypeId;
    @Column(name = "encounter_type_name")
    @ApiModelProperty(name = "会诊类型名称", example = "encountertypetest")
    private String encounterTypeName;
    @Column(name = "room_id")
    @ApiModelProperty(name = "诊室id", example = "1")
    private Integer roomId;
    @Column(name = "room_name")
    @ApiModelProperty(name = "诊室名称", example = "1")
    private String roomName;
    @Column(name = "appointment_date")
    @ApiModelProperty(name = "预约日期id", example = "2019-01-12")
    private Date appointmentDate;
    @Column(name = "status")
    @ApiModelProperty(name = "预约能否到达", example = "1")
    private String status;
    @Column(name = "attendance_status")
    @ApiModelProperty(name = "是否能到达", example = "1")
    private String attendanceStatus;
    @Column(name = "attendance_time")
    @ApiModelProperty(name = "到达", example = "2019-02-20 11:11:39")
    private Date attendanceTime;

    public Appointment() {
    }

    public void update(String status) throws Exception {
        if (status.equals("Attend")) {
            if (this.attendanceStatus.equals("Not Attend")) {
                this.attendanceStatus = status;
            } else {
                throw new Exception("Illegal");
            }
        }
    }

    public Appointment(Integer patientId, Integer clinicId,
                       Integer encounterTypeId, Integer roomId, Date appointmentDate, String attendanceStatus,
                       String patientDoc, String patientName, String encounterTypeName, String roomName, String clinicName) {
        this.patientId = patientId;
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.roomId = roomId;
        this.appointmentDate = appointmentDate;
        this.attendanceStatus = attendanceStatus;
        this.patientDoc = patientDoc;
        this.patientName = patientName;
        this.encounterTypeName = encounterTypeName;
        this.roomName = roomName;
        this.clinicName = clinicName;
    }

    public Appointment(Integer appointmentId, Integer patientId, Integer clinicId, Integer encounterTypeId, Integer roomId, Date appointmentDate,
                       String status, String attendanceStatus, Date attendanceTime) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicId = clinicId;
        this.encounterTypeId = encounterTypeId;
        this.roomId = roomId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.attendanceStatus = attendanceStatus;
        this.attendanceTime = attendanceTime;
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
