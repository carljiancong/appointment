package com.harmonycloud.repository;

import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.entity.Appointment;
import com.harmonycloud.entity.AppointmentQuota;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentQuotaRepository extends JpaRepository<AppointmentQuota,Integer> {


    List<AppointmentQuota> findByClinicIdAndEncounterTypeIdAndAppointmentDateContaining(Integer clinicId, Integer encounterTypeId, String monthYear);

    AppointmentQuota findByClinicIdAndEncounterTypeIdAndRoomIdAndAppointmentDate(Integer clinicId, Integer encounterTypeId,Integer roomId,String appointmentData);
}
