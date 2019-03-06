package com.harmonycloud.service;

import com.harmonycloud.entity.Holiday;
import com.harmonycloud.repository.HolidayRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HolidayService {

    @Resource
    private HolidayRepository holidayRepository;

    @Cacheable(value = "holiday", unless = "#result == null")
    public Set<String> getHolidayDate() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Holiday> holidayList = null;
        Set<String> holidayDateSet = new HashSet<>();
        try {
            holidayList = holidayRepository.findAll();
            for (Holiday hb : holidayList) {
                holidayDateSet.add(hb.getHolidayDate().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return holidayDateSet;
    }

}
