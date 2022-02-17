package com.payconiq.api.tests;

import com.payconiq.api.base.TestBase;
import com.payconiq.api.dto.request.RequestParamsDTO;
import com.payconiq.api.dto.response.booking.CreateBookingRespDTO;
import com.payconiq.api.dto.response.booking.GetBookingIdsResponseDTO;
import com.payconiq.api.dto.response.booking.GetBookingRespDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.payconiq.api.constants.BookingParams.*;

@Epic("Payconiq API Tests")
@Feature("Get Booking Ids API")
public class GetBookingIdsTest extends TestBase {

    private CreateBookingRespDTO createBookingRespDTO;
    private GetBookingIdsResponseDTO[] getBookingIdsRespDTO;

    @BeforeEach
    public void beforeEach() {
        Map<String, Object> createBookingPayload = new HashMap<>();
        RequestParamsDTO requestParams = new RequestParamsDTO();
        //construct payload
        createBookingPayload.put(FIRST_NAME, RandomStringUtils.randomAlphabetic(8));
        createBookingPayload.put(LAST_NAME, RandomStringUtils.randomAlphabetic(8));
        createBookingPayload.put(BOOKING_DATES_CHECKIN, dtf.format(now));
        createBookingPayload.put(BOOKING_DATES_CHECKOUT, dtf.format(now.plusDays(7)));
        requestParams.setUpdatePayload(createBookingPayload);
        createBookingRespDTO = bookingSteps.createBooking(requestParams);
    }


    @Test
    @DisplayName("Get All Bookings Successful")
    @Description("Returns the ids of all the bookings that exist within the API")
    public void getAllBookingsSuccessTest() {

        RequestParamsDTO requestParams = new RequestParamsDTO();

        getBookingIdsRespDTO = bookingSteps.getAllBookingIds(requestParams);

        Assert.assertEquals(200, getBookingIdsRespDTO[0].getStatusCode());
        Assert.assertTrue(getBookingIdsRespDTO.length > 0);
    }

    @Test
    @DisplayName("Get All Bookings by Name")
    @Description("Returns the ids of all the bookings that matches name and exist within the API")
    public void getAllBookingsWithNameTest() {

        RequestParamsDTO requestParams = new RequestParamsDTO();
        HashMap<String, String> queryParams = new HashMap<>();

        queryParams.put(FIRST_NAME, createBookingRespDTO.getBooking().getFirstname());
        queryParams.put(LAST_NAME, createBookingRespDTO.getBooking().getLastname());
        requestParams.setUpdateQueryParams(queryParams);

        getBookingIdsRespDTO = bookingSteps.getAllBookingIds(requestParams);

        for(GetBookingIdsResponseDTO bookingRespDTO : getBookingIdsRespDTO) {
            Assert.assertEquals(200, bookingRespDTO.getStatusCode());
            requestParams = new RequestParamsDTO();
            requestParams.setUpdatePathParams(new HashMap<String,String>(){{put(BOOKING_ID_PATH_PARAMS,bookingRespDTO.getBookingid().toString());}});
            GetBookingRespDTO getBookingRespDTO = bookingSteps.getBookingById(requestParams);
            Assert.assertEquals(createBookingRespDTO.getBooking().getFirstname(), getBookingRespDTO.getFirstname());
            Assert.assertEquals(createBookingRespDTO.getBooking().getLastname(), getBookingRespDTO.getLastname());
        }
    }

    @Test
    @DisplayName("Get All Bookings by Check-in / Checkout Date")
    @Description("Returns the ids of all the bookings that matches check-in/checkout date and exist within the API")
    public void getAllBookingsWithCheckinCheckoutDateTest() {

        RequestParamsDTO requestParams = new RequestParamsDTO();
        HashMap<String, String> queryParams = new HashMap<>();

        queryParams.put(CHECK_IN, createBookingRespDTO.getBooking().getBookingdates().getCheckin());
        queryParams.put(CHECK_OUT, createBookingRespDTO.getBooking().getBookingdates().getCheckout());
        requestParams.setUpdateQueryParams(queryParams);

        getBookingIdsRespDTO = bookingSteps.getAllBookingIds(requestParams);

        for(GetBookingIdsResponseDTO bookingRespDTO : getBookingIdsRespDTO) {
            Assert.assertEquals(200, bookingRespDTO.getStatusCode());
            requestParams = new RequestParamsDTO();
            requestParams.setUpdatePathParams(new HashMap<String,String>(){{put(BOOKING_ID_PATH_PARAMS,bookingRespDTO.getBookingid().toString());}});
            GetBookingRespDTO getBookingRespDTO = bookingSteps.getBookingById(requestParams);
            Assert.assertEquals(createBookingRespDTO.getBooking().getBookingdates().getCheckin(), getBookingRespDTO.getBookingdates().getCheckin());
            Assert.assertEquals(createBookingRespDTO.getBooking().getBookingdates().getCheckout(), getBookingRespDTO.getBookingdates().getCheckout());
        }
    }



}
