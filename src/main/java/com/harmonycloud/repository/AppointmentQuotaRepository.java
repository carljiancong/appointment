package com.harmonycloud.repository;

import com.harmonycloud.entity.AppointmentQuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentQuotaRepository extends JpaRepository<AppointmentQuota,Integer> {
}
