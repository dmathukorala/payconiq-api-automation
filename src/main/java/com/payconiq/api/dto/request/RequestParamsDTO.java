package com.payconiq.api.dto.request;

import io.restassured.http.ContentType;
import lombok.Data;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

@Data
public class RequestParamsDTO {

    /**
     * Update value maps : can be used to set values that needs to be added/updated from default request template.
     *
     * updateHeaders : to add/update header values
     * updatePayload : to add/update request payload values
     * updatePathParams : to create/update path parameters
     * updateQueryParams : to add/update query parameters
     * updateFormParams : to add/update form parameters
     */
    private Map<String, String> updateHeaders;
    private Map<String, Object> updatePayload;
    private Map<String, String> updatePathParams;
    private Map<String, String> updateQueryParams;
    private Map<String, String> updateFormParams;
    private String updateEncoderContentType;
    private ContentType updateEncoder;
    private String updateMultiPartControlName;
    private Object updateMultiPartObject;

    /**
     * Set request body.
     * If this is set, payload from request template and updatePayload parameters are ignored.
     */
    private JSONObject body;

    /**
     * Set urlEncodingEnabled value, default : false
     */
    private boolean urlEncodingEnabled;

    /**
     * Remove value lists : can be used to remove values from default request template.
     * key of the value to be removed should be added to the list
     *
     * removeHeaders : to remove header values
     * removePayload : to remove request payload values
     * removePathParams : to remove path parameters
     */
    private List<String> removeHeaders;
    private List<String> removePayload;
    private List<String> removePathParams;
    private List<String> removeQueryParams;
    private List<String> removeFormParams;
    private String removeMultiPartControlName;
    private Object removeMultiPartObject;

}
