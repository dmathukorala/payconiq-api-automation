package com.payconiq.api.tests;

import com.payconiq.api.base.TestBase;
import com.payconiq.api.dto.request.RequestParamsDTO;
import com.payconiq.api.dto.response.booking.CreateBookingRespDTO;
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
@Feature("Get Booking API")
public class GetBookingTest extends TestBase {

    private CreateBookingRespDTO createBookingRespDTO;
    private GetBookingRespDTO getBookingRespDTO;

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
    @DisplayName("Get Booking Successful")
    @Description("Get successful booking with parameters")
    public void getBookingSuccessTest() {

        Map<String, String> pathParams = new HashMap<>();
        RequestParamsDTO requestParams = new RequestParamsDTO();
        pathParams.put(BOOKING_ID_PATH_PARAMS, createBookingRespDTO.getBookingid().toString());
        requestParams.setUpdatePathParams(pathParams);

        getBookingRespDTO = bookingSteps.getBookingById(requestParams);

        Assert.assertEquals(200, getBookingRespDTO.getStatusCode());
        Assert.assertEquals(createBookingRespDTO.getBooking().getFirstname(), getBookingRespDTO.getFirstname());
    }

}
