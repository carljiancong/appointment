package com.harmonycloud.repository;

import com.harmonycloud.entity.AppointmentQuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentQuotaRepository extends JpaRepository<AppointmentQuota,Integer> {

    @Modifying
    @Query()
    List<AppointmentQuota> findByMonth();
}
