package com.payconiq.api.httpmethods;

import io.restassured.http.ContentType;

import java.util.Map;

public class RequestBuilder {
    private String url;
    private String payload;
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private Map<String, String> formParams;
    private String multiPartControlName;
    private Object multiPartObject;
    private String updateEncoderContentType;
    private ContentType updateEncoder;
    private boolean urlEncodingEnabled;

    public String getPayload() {
        return payload;
    }

    public RequestBuilder setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RequestBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public RequestBuilder setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public RequestBuilder setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public Map<String, String> getFormParams() {
        return formParams;
    }

    public RequestBuilder setFormParams(Map<String, String> formParams) {
        this.formParams = formParams;
        return this;
    }

    public String getMultiPartControlName() {
        return multiPartControlName;
    }

    public RequestBuilder setMultiPartControlName(String multiPartControlName) {
        this.multiPartControlName = multiPartControlName;
        return this;
    }

    public Object getMultiPartObject() {
        return multiPartObject;
    }

    public void setMultiPartObject(Object multiPartObject) {
        this.multiPartObject = multiPartObject;
    }

    public String getUpdateEncoderContentType() {
        return updateEncoderContentType;
    }

    public RequestBuilder setUpdateEncoderContentType(String updateEncoderContentType) {
        this.updateEncoderContentType = updateEncoderContentType;
        return this;
    }

    public ContentType getUpdateEncoder() {
        return updateEncoder;
    }

    public RequestBuilder setUpdateEncoder(ContentType updateEncoder) {
        this.updateEncoder = updateEncoder;
        return this;
    }

    public RequestBuilder setUrlEncodingEnabled(Boolean urlEncodingEnabled) {
        this.urlEncodingEnabled = urlEncodingEnabled;
        return this;
    }

    public RequestHandler build() {
        return new RequestHandler(url, payload, headers, queryParams, formParams, multiPartControlName, multiPartObject, urlEncodingEnabled);
    }

}
