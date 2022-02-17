package com.payconiq.api.steps;


import com.payconiq.api.base.ServiceBase;
import com.payconiq.api.constants.RequestIdentifier;
import com.payconiq.api.dto.response.base.BaseResponseDTO;
import com.payconiq.api.dto.request.RequestParamsDTO;
import com.payconiq.api.dto.response.booking.GetBookingIdsResponseDTO;
import com.payconiq.api.dto.response.booking.CreateBookingRespDTO;
import com.payconiq.api.dto.response.booking.GetBookingRespDTO;
import com.payconiq.api.httpmethods.RequestHandler;
import com.payconiq.api.requesthandler.ServiceManager;
import io.qameta.allure.Step;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;


public class BookingSteps extends ServiceBase {

    private ServiceManager serviceManager;
    private Log log = LogFactory.getLog(RequestHandler.class);

    public BookingSteps() {
        serviceManager = new ServiceManager();
    }

    @Step("Create new booking")
    public CreateBookingRespDTO createBooking(RequestParamsDTO requestParams) {
        CreateBookingRespDTO createBookingRespDTO = null;
        try {
            BaseResponseDTO baseResponseDTO = serviceManager.sendRequest(RequestIdentifier.booking_CreateBooking, requestParams);

            createBookingRespDTO = (CreateBookingRespDTO) getResponseDTO(baseResponseDTO.getResponse(), CreateBookingRespDTO.class);
            createBookingRespDTO.setResponse(baseResponseDTO.getResponse());

        } catch (Exception e) {
            log.error(e);
            Assert.fail(e.toString());
        }
        return createBookingRespDTO;
    }

    @Step("update existing booking")
    public GetBookingRespDTO updateBooking(RequestParamsDTO requestParams) {
        GetBookingRespDTO bookingRespDTO = null;
        try {
            BaseResponseDTO baseResponseDTO = serviceManager.sendRequest(RequestIdentifier.booking_UpdateBooking, requestParams);

            bookingRespDTO = (GetBookingRespDTO) getResponseDTO(baseResponseDTO.getResponse(), GetBookingRespDTO.class);
            bookingRespDTO.setResponse(baseResponseDTO.getResponse());

        } catch (Exception e) {
            log.error(e);
            Assert.fail(e.toString());
        }
        return bookingRespDTO;
    }

    @Step("Get booking by booking id")
    public GetBookingRespDTO getBookingById(RequestParamsDTO requestParams) {
        GetBookingRespDTO getBookingRespDTO = null;
        try {
            BaseResponseDTO baseResponseDTO = serviceManager.sendRequest(RequestIdentifier.booking_GetBooking, requestParams);

            getBookingRespDTO = (GetBookingRespDTO) getResponseDTO(baseResponseDTO.getResponse(), GetBookingRespDTO.class);
            getBookingRespDTO.setResponse(baseResponseDTO.getResponse());

        } catch (Exception e) {
            log.error(e);
            Assert.fail(e.toString());
        }
        return getBookingRespDTO;
    }

    @Step("Get all booking ids by filter")
    public GetBookingIdsResponseDTO[] getAllBookingIds(RequestParamsDTO requestParams) {
        GetBookingIdsResponseDTO[] getBookingIdsDTO = null;
        try {
            BaseResponseDTO baseResponseDTO = serviceManager.sendRequest(RequestIdentifier.booking_GetBookingIds, requestParams);

            getBookingIdsDTO = (GetBookingIdsResponseDTO[]) getResponseDTO(baseResponseDTO.getResponse(), GetBookingIdsResponseDTO[].class);
            getBookingIdsDTO[0].setResponse(baseResponseDTO.getResponse());

        } catch (Exception e) {
            log.error(e);
            Assert.fail(e.toString());
        }
        return getBookingIdsDTO;
    }

    @Step("Partially update existing booking")
    public GetBookingRespDTO updatePartialBooking(RequestParamsDTO requestParams) {
        GetBookingRespDTO bookingRespDTO = null;
        try {
            BaseResponseDTO baseResponseDTO = serviceManager.sendRequest(RequestIdentifier.booking_PartialUpdateBooking, requestParams);

            bookingRespDTO = (GetBookingRespDTO) getResponseDTO(baseResponseDTO.getResponse(), GetBookingRespDTO.class);
            bookingRespDTO.setResponse(baseResponseDTO.getResponse());

        } catch (Exception e) {
            log.error(e);
            Assert.fail(e.toString());
        }
        return bookingRespDTO;
    }

    @Step("Delete existing booking")
    public BaseResponseDTO deleteBooking(RequestParamsDTO requestParams) {
        BaseResponseDTO baseResponseDTO = null;
        try {
            baseResponseDTO = serviceManager.sendRequest(RequestIdentifier.booking_PartialUpdateBooking, requestParams);

        } catch (Exception e) {
            log.error(e);
            Assert.fail(e.toString());
        }
        return baseResponseDTO;
    }
}
