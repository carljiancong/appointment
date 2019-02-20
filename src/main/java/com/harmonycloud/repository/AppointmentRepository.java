package com.harmonycloud.repository;

import com.harmonycloud.dto.AppoinmentDto;
import com.harmonycloud.entity.Appoinment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appoinment,Integer> {

    /**
     * 根据 patient id 查询该病人的预约情况
     * @param patientId
     * @return
     */
    List<AppoinmentDto> findByPatientId(Integer patientId);
}
