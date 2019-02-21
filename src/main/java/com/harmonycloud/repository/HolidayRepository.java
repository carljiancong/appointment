package com.harmonycloud.repository;

import com.harmonycloud.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday,Integer> {

    @Modifying
    @Query("select new com.harmonycloud.bo.HolidayBo(h.date) from Holiday h where h.date like concat('%',?1)")
    List<String> findByMonthYear(String monthYear);

}
