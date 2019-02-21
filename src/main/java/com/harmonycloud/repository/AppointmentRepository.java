package com.harmonycloud.repository;

import com.harmonycloud.bo.AppoinmentBo;
import com.harmonycloud.entity.Appoinment;
import com.harmonycloud.entity.AppointmentQuota;
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
    List<AppoinmentBo> findByPatientId(Integer patientId);

}
