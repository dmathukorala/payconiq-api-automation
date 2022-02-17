package com.payconiq.api.dto.response.base;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Objects;

public class BaseResponseDTO {

    @Getter
    private Response response;
    @Getter
    @Setter
    private int statusCode;
    @Getter
    private String contentType;
    @Getter
    private String body;
    @Getter
    private Headers header;

    public void setResponse(Response response) {
        Log log = LogFactory.getLog(BaseResponseDTO.class);

        this.response = response;
        this.statusCode = response.statusCode();
        this.contentType = response.contentType();
        if (response.getBody() != null)
            this.body = response.getBody().asString();
        if (response.getHeaders() != null)
            this.header = response.getHeaders();

        log.info("\n Response Headers << " + Objects.requireNonNull(response.getHeaders()).asList());
        log.info("\n Response Body << " + response.asString());
    }

    public Response getResponse(Response response) {
        return response;
    }
}
