package com.payconiq.api.constants;

public enum RequestIdentifier {
    /**
     * RequestIdentifiers - place in alphabetical order
     * Format : <serviceName>_<apiTemplateName>
     * serviceName : Should be same as service directory name in test/resources/ServiceData
     * apiTemplateName : Should be same as api template file name in test/resources/ServiceData/<serviceName>
     */

    //booking endpoints
    booking_CreateBooking,
    booking_GetBookingIds,
    booking_GetBooking,
    booking_UpdateBooking,
    booking_PartialUpdateBooking,
    booking_DeleteBooking,

    //auth endpoints
    auth_CreateToken
}
