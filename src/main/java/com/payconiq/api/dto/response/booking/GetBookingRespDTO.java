package com.payconiq.api.dto.response.booking;

import com.payconiq.api.dto.response.base.BaseResponseDTO;
import lombok.Data;

@Data
public class GetBookingRespDTO extends BaseResponseDTO {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

}
