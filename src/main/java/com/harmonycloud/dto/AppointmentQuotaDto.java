package com.harmonycloud.dto;

import com.harmonycloud.bo.AppointmentQuotaBo;
import com.harmonycloud.entity.AppointmentQuota;
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
    private AppointmentQuota appointmentQuota;
    @ApiModelProperty()
    private boolean isHolidayDate;

    public AppointmentQuotaDto() {
    }

    public AppointmentQuotaDto(AppointmentQuota appointmentQuota, boolean isHolidayDate) {
        this.appointmentQuota = appointmentQuota;
        this.isHolidayDate = isHolidayDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public AppointmentQuota getAppointmentQuota() {
        return appointmentQuota;
    }

    public void setAppointmentQuota(AppointmentQuota appointmentQuota) {
        this.appointmentQuota = appointmentQuota;
    }

    public boolean isHolidayDate() {
        return isHolidayDate;
    }

    public void setHolidayDate(boolean holidayDate) {
        isHolidayDate = holidayDate;
    }
}
