package com.harmonycloud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author qidong
 * @String 2019/2/13
 */
@Entity
@Table(name = "holiday")
public class Holiday implements Serializable {
    @Id
    private Integer holidayId;
    @Column(name = "holiday_date")
    private String holidayDate;
    @Column(name = "holiday_description")
    private String holidayDescription;

    public Holiday() {
    }

    public Holiday(Integer holidayId, String holidayDate, String holidayDescription) {
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
