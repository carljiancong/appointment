package com.harmonycloud.service;

import com.harmonycloud.entity.Holiday;
import com.harmonycloud.repository.HolidayRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author qidong
 * @date 2019/2/21
 */

@Service
public class HolidayService {

    @Resource
    private HolidayRepository holidayRepository;

    @Cacheable(value = "holiday", unless = "#result == null")
    public Set<String> getHolidayDate() {
        List<Holiday> holidayList = null;
        Set<String> holidayDateSet = new HashSet<>();
        try {
            holidayList = holidayRepository.findAll();
            for (Holiday hb : holidayList) {
                holidayDateSet.add(hb.getHolidayDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return holidayDateSet;
    }

}
