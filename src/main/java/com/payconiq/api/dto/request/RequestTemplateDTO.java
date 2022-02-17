package com.payconiq.api.dto.request;

import io.restassured.http.ContentType;
import lombok.Data;

import java.util.Map;

@Data
public class RequestTemplateDTO {

    private String serviceName;
    private String apiName;
    private String baseUrl;
    private String relativeUrl;
    private String method;
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private Map<String, String> formParams;
    private Object payload;
    private String encoderContentType;
    private ContentType encoder;
    private String multiPartControlName;
    private Object multiPartObject;
    private String status;

}
