package com.payconiq.api.tests;

import com.payconiq.api.base.TestBase;
import com.payconiq.api.dto.request.RequestParamsDTO;
import com.payconiq.api.dto.response.booking.CreateBookingRespDTO;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.payconiq.api.constants.BookingParams.*;

@Epic("Payconiq API Tests")
@Feature("Create Booking API")
public class CreateBookingTest extends TestBase {


    @Test
    @DisplayName("Create Booking Successful")
    @Description("Create successful booking with parameters")
    public void createBookingSuccessTest() {
        RequestParamsDTO requestParams = new RequestParamsDTO();
        Map<String, Object> createBookingPayload = new HashMap<>();

        //construct payload
        createBookingPayload.put(FIRST_NAME, RandomStringUtils.randomAlphabetic(8));
        createBookingPayload.put(LAST_NAME, RandomStringUtils.randomAlphabetic(8));
        createBookingPayload.put(BOOKING_DATES_CHECKIN, dtf.format(now));
        createBookingPayload.put(BOOKING_DATES_CHECKOUT, dtf.format(now.plusDays(7)));

        requestParams.setUpdatePayload(createBookingPayload);

        CreateBookingRespDTO responseDTO = bookingSteps.createBooking(requestParams);

        Assert.assertEquals(200, responseDTO.getStatusCode());
        Assert.assertNotNull(responseDTO.getBookingid());
    }

    @Test
    @DisplayName("Create Booking Without First Name")
    @Description("Create booking without first name parameter")
    public void createBookingWithoutFirstNameTest() {
        RequestParamsDTO requestParams = new RequestParamsDTO();
        List<String> removeParams = new ArrayList<>();

        removeParams.add(FIRST_NAME);
        requestParams.setRemovePayload(removeParams);

        CreateBookingRespDTO responseDTO = bookingSteps.createBooking(requestParams);

        Assert.assertEquals(400, responseDTO.getStatusCode());
    }

}
