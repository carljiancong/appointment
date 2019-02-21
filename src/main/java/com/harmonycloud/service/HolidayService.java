package com.harmonycloud.service;

import com.harmonycloud.entity.Holiday;
import com.harmonycloud.repository.HolidayRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qidong
 * @date 2019/2/21
 */

@Service
public class HolidayService {

    @Resource
    private HolidayRepository holidayRepository;

    @Cacheable(value = "holiday", unless = "#result == null")
    public List<String> getHolidayDate(String monthYear) {
        List<String> holidayDateList = null;
        try {
            holidayDateList = holidayRepository.findByMonthYear(monthYear);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return holidayDateList;
    }
}
