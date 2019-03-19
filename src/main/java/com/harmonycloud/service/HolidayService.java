package com.harmonycloud.service;

import com.harmonycloud.entity.Holiday;
import com.harmonycloud.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    /**
     * get the holiday to redis
     *
     * @return
     */
    @Cacheable(value = "holiday", unless = "#result == null")
    public Set<String> getHolidayDate() throws Exception {

        Set<String> holidayDateSet = new HashSet<>();

        List<Holiday> holidayList = holidayRepository.findAll();
        for (Holiday hb : holidayList) {
            holidayDateSet.add(hb.getHolidayDate().toString());
        }

        return holidayDateSet;
    }

}
