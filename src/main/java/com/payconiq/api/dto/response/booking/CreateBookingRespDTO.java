package com.payconiq.api.dto.response.booking;

import com.payconiq.api.dto.response.base.BaseResponseDTO;
import lombok.Data;

@Data
public class CreateBookingRespDTO extends BaseResponseDTO {

    private Integer bookingid;
    private Booking booking;
}
