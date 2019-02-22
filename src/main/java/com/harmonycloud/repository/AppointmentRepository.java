package com.harmonycloud.repository;


import com.harmonycloud.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    /**
     * 根据 patient id 查询该病人的预约情况
     * @param patientId
     * @return
     */
    List<Appointment> findByPatientId(Integer patientId);

    /**
     * 查询病人是否已有相同的预约记录
     * @param patientId
     * @param encounterTypeId
     * @param roomId
     * @return
     */
    List<Appointment> findByPatientIdAndEncounterTypeIdAndRoomIdAndAttendanceStatus(Integer patientId,Integer encounterTypeId,Integer roomId,String attendanceStatus);

//    @Query("select new com.harmonycloud.entity.Appointment(a.appointmentId, a.patientId, a.clinicId, a.encounterTypeId," +
//            "a.roomId, a.appointmentDate, a.status, a.attendanceStatus, a.attendanceTime) from Appointment a where a.date = '?1%'")
    List<Appointment> findByAppointmentDateContaining(String appointmentDate);

//    @Query("select new com.harmonycloud.entity.Appointment(a.appointmentId, a.patientId, a.clinicId, a.encounterTypeId," +
//            "a.roomId, a.appointmentDate, a.status, a.attendanceStatus, a.attendanceTime) from Appointment a where a.roomId = ?1 and a.appointmentDate = '?2%'")
    List<Appointment> findByRoomIdAndAppointmentDateContaining(Integer roomId, String appointmentDate);

//    @Query("select new com.harmonycloud.entity.Appointment(a.appointmentId, a.patientId, a.clinicId, a.encounterTypeId," +
//            "a.roomId, a.appointmentDate, a.status, a.attendanceStatus, a.attendanceTime) from Appointment a where a.attendanceStatus = ?1 and a.appointmentDate = '?2%'")
    List<Appointment> findByAttendanceStatusAndAppointmentDateContaining(String attendanceStatus, String appointmentDate);

//    @Query("select new com.harmonycloud.entity.Appointment(a.appointmentId, a.patientId, a.clinicId, a.encounterTypeId," +
//            "a.roomId, a.appointmentDate, a.status, a.attendanceStatus, a.attendanceTime) from Appointment a where a.roomId = ?1 and " +
//            "a.attendanceStatus = ?2 and a.appointmentDate = '?3%'")
    List<Appointment> findByRoomIdAndAttendanceStatusAndAppointmentDateContaining(Integer roomId, String attendanceStatus, String appointmentDate);
}
