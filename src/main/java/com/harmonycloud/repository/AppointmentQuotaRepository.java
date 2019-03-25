package com.harmonycloud.repository;

import com.harmonycloud.entity.AppointmentQuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentQuotaRepository extends JpaRepository<AppointmentQuota, Integer> {


    @Query(nativeQuery = true, value = "select  * from \"appointment_quota\" where \"clinic_id\" = ?1 and \"encounter_type_id\"=?2\n" +
            "                                    and to_char(\"appointment_date\",'yyyy-mm-dd') like concat(?3,'%')")
    List<AppointmentQuota> findByQuotalist(Integer clinicId, Integer encounterTypeId, String monthYear);


    @Query(nativeQuery = true, value = "select  * from \"appointment_quota\" where \"clinic_id\" = ?1 and \"encounter_type_id\"=?2\n" +
            "                                    and \"room_id\"=?3 and to_char(\"appointment_date\",'yyyy-mm-dd') like concat(?4,'%')")
    AppointmentQuota findByappointmentDate(Integer clinicId, Integer encounterTypeId, Integer roomId, String appointmentData);
}
