package com.harmonycloud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author qidong
 * @date 2019/2/13
 */
@Entity
@Table(name = "holiday")
public class Holiday {
    @Id
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

    public Integer getId() {
        return holidayId;
    }

    public void setId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public Date getDate() {
        return holidayDate;
    }

    public void setDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getDescription() {
        return holidayDescription;
    }

    public void setDescription(String holidayDescription) {
        this.holidayDescription = holidayDescription;
    }
}
