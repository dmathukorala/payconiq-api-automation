package com.payconiq.api.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class ServiceBase {

    public static <T> Object getResponseDTO(Response response, Class<T> responseType) throws java.io.IOException, IllegalAccessException, InstantiationException {
        Object responseDTO = null;
        ObjectMapper objectMapper = new ObjectMapper();
        if (String.valueOf(response.statusCode()).startsWith("2") && response.body() != null) {
            responseDTO = objectMapper.readValue(response.asString(), responseType);
            return responseDTO;

        } else {
            T inst = responseType.newInstance();
            return inst;
        }
    }
}
