package com.harmonycloud.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "holiday")
public class Holiday implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer holidayId;

    @Column(name = "holiday_date")
    private LocalDate holidayDate;

    @Column(name = "holiday_description")
    private String holidayDescription;

    public Holiday() {
    }

    public Holiday(Integer holidayId, LocalDate holidayDate, String holidayDescription) {
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

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayDescription() {
        return holidayDescription;
    }

    public void setHolidayDescription(String holidayDescription) {
        this.holidayDescription = holidayDescription;
    }
}
