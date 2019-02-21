package com.harmonycloud.repository;


import com.harmonycloud.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
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
    List<Appointment> findByPatientIdAndEncounterTypeIdAndRoomIdAndAttendanceStatus(Integer patientId,Integer encounterTypeId,Integer roomId,String attendanceSatus);

}
