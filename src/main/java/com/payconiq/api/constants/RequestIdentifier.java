package com.payconiq.api.constants;

public class RequestIdentifier {

    private RequestIdentifier() {}

    /**
     * RequestIdentifiers - place in alphabetical order
     * Format : <serviceName>_<apiTemplateName>
     * serviceName : Should be same as service directory name in test/resources/ServiceData
     * apiTemplateName : Should be same as api template file name in test/resources/ServiceData/<serviceName>
     */

    //booking endpoints
    public static final String BOOKING_CREATE_BOOKING = "booking_CreateBooking";
    public static final String BOOKING_GET_BOOKING_IDS ="booking_GetBookingIds";
    public static final String BOOKING_GET_BOOKING ="booking_GetBooking";
    public static final String BOOKING_UPDATE_BOOKING ="booking_UpdateBooking";
    public static final String BOOKING_PARTIAL_UPDATE_BOOKING ="booking_PartialUpdateBooking";
    public static final String BOOKING_DELETE_BOOKING ="booking_DeleteBooking";

    //auth endpoints
    public static final String AUTH_CREATE_TOKEN ="auth_CreateToken";
}
