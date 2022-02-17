package com.payconiq.api.tests;

import com.payconiq.api.base.TestBase;
import com.payconiq.api.dto.request.RequestParamsDTO;
import com.payconiq.api.dto.response.auth.CreateTokenDTO;
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

import static com.payconiq.api.constants.AuthParams.COOKIE;
import static com.payconiq.api.constants.AuthParams.TOKEN;
import static com.payconiq.api.constants.BookingParams.*;

@Epic("Payconiq API Tests")
@Feature("Update Booking API")
public class UpdateBookingTest extends TestBase {


    private CreateBookingRespDTO createBookingRespDTO;
    private CreateTokenDTO authTokenDTO;

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
        authTokenDTO = authSteps.createAuthToken(new RequestParamsDTO());
    }


    @Test
    @DisplayName("Update Booking Successful")
    @Description("Update existing booking with valid parameters")
    public void updateBookingSuccessTest(){
        RequestParamsDTO requestParams = new RequestParamsDTO();
        Map<String, Object> updateBookingPayload = new HashMap<>();
        Map<String, String> pathParams = new HashMap<>();
        Map<String, String> headers = new HashMap<>();

        String firstName = RandomStringUtils.randomAlphabetic(8);
        String lastName = RandomStringUtils.randomAlphabetic(8);

        //construct payload
        updateBookingPayload.put(FIRST_NAME, firstName);
        updateBookingPayload.put(LAST_NAME, lastName);
        pathParams.put(BOOKING_ID_PATH_PARAMS, createBookingRespDTO.getBookingid().toString());
        headers.put(COOKIE, TOKEN+authTokenDTO.getToken());

        requestParams.setUpdatePayload(updateBookingPayload);
        requestParams.setUpdatePathParams(pathParams);
        requestParams.setUpdateHeaders(headers);

        GetBookingRespDTO responseDTO = bookingSteps.updateBooking(requestParams);

        Assert.assertEquals(200, responseDTO.getStatusCode());
        Assert.assertEquals(firstName, responseDTO.getFirstname());
        Assert.assertEquals(lastName, responseDTO.getLastname());

    }

    @Test
    @DisplayName("Update Booking Without Auth Token")
    @Description("Update existing booking Without Auth Token parameter")
    public void updateBookingWithoutTokenTest(){
        RequestParamsDTO requestParams = new RequestParamsDTO();
        Map<String, Object> updateBookingPayload = new HashMap<>();
        Map<String, String> pathParams = new HashMap<>();

        String firstName = RandomStringUtils.randomAlphabetic(8);
        String lastName = RandomStringUtils.randomAlphabetic(8);

        //construct payload
        updateBookingPayload.put(FIRST_NAME, firstName);
        updateBookingPayload.put(LAST_NAME, lastName);
        pathParams.put(BOOKING_ID_PATH_PARAMS, createBookingRespDTO.getBookingid().toString());

        requestParams.setUpdatePayload(updateBookingPayload);
        requestParams.setUpdatePathParams(pathParams);

        GetBookingRespDTO responseDTO = bookingSteps.updateBooking(requestParams);

        Assert.assertEquals(403, responseDTO.getStatusCode());


    }

}
