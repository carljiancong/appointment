package com.harmonycloud.dto;

import com.harmonycloud.bo.AppointmentQuotaBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author qidong
 * @date 2019/2/21
 */
@ApiModel
public class AppointmentQuotaDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty()
    private AppointmentQuotaBo appointmentQuotaBo;
    @ApiModelProperty()
    private boolean isHolidayDate;

    public AppointmentQuotaDto() {
    }

    public AppointmentQuotaDto(AppointmentQuotaBo appointmentQuotaBo, boolean isHolidayDate) {
        this.appointmentQuotaBo = appointmentQuotaBo;
        this.isHolidayDate = isHolidayDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public AppointmentQuotaBo getAppointmentQuotaBo() {
        return appointmentQuotaBo;
    }

    public void setAppointmentQuotaBo(AppointmentQuotaBo appointmentQuotaBo) {
        this.appointmentQuotaBo = appointmentQuotaBo;
    }

    public boolean isHolidayDate() {
        return isHolidayDate;
    }

    public void setHolidayDate(boolean holidayDate) {
        isHolidayDate = holidayDate;
    }
}
