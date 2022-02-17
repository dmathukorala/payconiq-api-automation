package com.payconiq.api.dto.response.booking;

import lombok.Data;

@Data
public class Booking {

    private String firstname;
    private String lastname;
    private float totalprice;
    private boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

}
