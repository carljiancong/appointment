package com.harmonycloud.repository;

import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.entity.AppointmentQuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentQuotaRepository extends JpaRepository<AppointmentQuota,Integer> {

    @Modifying
    @Query("select new com.harmonycloud.bo.AppointmentQuotaBo(aq.appointmentQuotaId, aq.clinicId, " +
            "aq.encounterTypeId, aq.roomId, aq.appointmentDate, aq.quota, aq.quotaBooked)" +
            " from AppointmentQuota aq where aq.clinicId = ?1 and aq.encounterTypeId = ?2 and aq.roomId = ?3 and aq.date like concat('%',?4)")
    List<AppointmentQuotaBo> findByMonth(Integer clinicId, Integer encounterTypeId, Integer roomId, String monthYear);
}
