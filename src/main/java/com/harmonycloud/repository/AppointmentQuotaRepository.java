package com.harmonycloud.repository;

import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.entity.Appointment;
import com.harmonycloud.entity.AppointmentQuota;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentQuotaRepository extends JpaRepository<AppointmentQuota,Integer> {


    @Query(nativeQuery = true,value = "select  * from \"appoinment_quota\" where \"clinic_id\" = ?1 and \"encounter_type_id\"=?2\n" +
            "                                    and to_char(\"appointment_date\",'yyyy-mm-dd') like concat(?3,'%')")
    List<AppointmentQuota> findByQuotalist(Integer clinicId, Integer encounterTypeId, String monthYear);


    @Query(nativeQuery = true,value = "select  * from \"appoinment_quota\" where \"clinic_id\" = ?1 and \"encounter_type_id\"=?2\n" +
            "                                    and \"room_id\"=?3 and to_char(\"appointment_date\",'yyyy-mm-dd') like concat(?4,'%')")
    AppointmentQuota findByappointmentDate(Integer clinicId, Integer encounterTypeId,Integer roomId,String appointmentData);
}
