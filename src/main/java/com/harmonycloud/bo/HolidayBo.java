package com.harmonycloud.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author qidong
 * @String 2019/2/21
 */
public class HolidayBo {
    private Integer holidayId;
    private String holidayDate;
    private String holidayDescription;

    public HolidayBo() {
    }

    public HolidayBo(Integer holidayId, String holidayDate, String holidayDescription) {
        this.holidayId = holidayId;
        this.holidayDate = holidayDate;
        this.holidayDescription = holidayDescription;
    }

    public Integer getId() {
        return holidayId;
    }

    public void setId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public String getDate() {
        return holidayDate;
    }

    public void setDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getDescription() {
        return holidayDescription;
    }

    public void setDescription(String holidayDescription) {
        this.holidayDescription = holidayDescription;
    }
}
