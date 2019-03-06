package com.harmonycloud.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qidong
 * @String 2019/2/13
 */
@Entity
@Table(name = "holiday")
public class Holiday implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer holidayId;
    @Column(name = "holiday_date")
    private Date holidayDate;
    @Column(name = "holiday_description")
    private String holidayDescription;

    public Holiday() {
    }

    public Holiday(Integer holidayId, Date holidayDate, String holidayDescription) {
        this.holidayId = holidayId;
        this.holidayDate = holidayDate;
        this.holidayDescription = holidayDescription;
    }

    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayDescription() {
        return holidayDescription;
    }

    public void setHolidayDescription(String holidayDescription) {
        this.holidayDescription = holidayDescription;
    }
}
