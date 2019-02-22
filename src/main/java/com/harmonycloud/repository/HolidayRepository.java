package com.harmonycloud.repository;

import com.harmonycloud.bo.HolidayBo;
import com.harmonycloud.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday,Integer> {

    @Modifying
    @Query("select new com.harmonycloud.entity.Holiday(h.holidayDate) from Holiday h where h.holidayDate = ?1")
    List<Holiday> findByMonthYear(String monthYear);


}
