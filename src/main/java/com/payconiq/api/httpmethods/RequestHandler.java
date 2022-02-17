package com.payconiq.api.httpmethods;

import com.github.dzieciou.testing.curl.CurlHandler;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.event.Level;


import javax.ws.rs.HttpMethod;
import java.util.*;

public class RequestHandler {

    private static final String GET_URL_LOG = "Get URL --> ";
    private String url;
    private String payload;
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private Map<String, String> formParams;
    private String multiPartControlName;
    private Object multiPartObject;
    private String contentType;
    private ContentType encoder;
    private boolean urlEncodingEnabled;
    private RestAssuredConfig config;
    private final List<String> curls = new ArrayList<>();
    private Log log = LogFactory.getLog(RequestHandler.class);

    public RequestHandler() {
    }

    public RequestHandler(String url, String payload, Map<String, String> headers, Map<String, String> queryParams, Map<String, String> formParams, String multiPartControlName, Object multiPartObject, boolean urlEncodingEnabled) {
        this.url = url;
        this.payload = payload;
        this.headers = headers;
        this.queryParams = queryParams;
        this.formParams = formParams;
        this.urlEncodingEnabled = urlEncodingEnabled;
        this.multiPartControlName = multiPartControlName;
        this.multiPartObject = multiPartObject;
    }

    private void customCurlHandling() {
        CurlHandler handler = (curl, options) -> {
            Options.builder().useLogLevel(Level.INFO).build();
            curls.add(curl);
        };
        config = CurlRestAssuredConfigFactory.createConfig(Collections.singletonList(handler));
    }

    private Response httpRequests(String request) {
        log.info(GET_URL_LOG + url);
        RequestSpecification spec = getRequestSpecification(RestAssured.given());
        customCurlHandling();
        Response res;
        RequestSpecification requestSpecification = spec.config(config).when();
        switch (request.toUpperCase()) {
            case HttpMethod.GET:
                res = requestSpecification.get(this.url);
                break;
            case HttpMethod.POST:
                res = requestSpecification.post(this.url);
                break;
            case HttpMethod.PUT:
                res = requestSpecification.put(this.url);
                break;
            case HttpMethod.DELETE:
                res = requestSpecification.delete(this.url);
                break;
            case HttpMethod.PATCH:
                res = requestSpecification.patch(this.url);
                break;
            default:
                throw new IllegalArgumentException("Invalid http request");
        }
        Response response = res.then().extract().response();
        log.info(curls.get(0));
        return response;
    }

    /**
     * @return response
     */
    public Response getResponse() {
        return httpRequests(HttpMethod.GET);
    }

    /**
     * @return response
     */
    public Response postResponse() {
        return httpRequests(HttpMethod.POST);
    }

    /**
     * @return response
     */
    public Response patchResponse() {
        return httpRequests(HttpMethod.PATCH);
    }

    /**
     * /**
     *
     * @return response
     */
    public Response putResponse() {
        return httpRequests(HttpMethod.PUT);
    }

    /**
     * @return response
     */
    public Response deleteResponse() {
        return httpRequests(HttpMethod.DELETE);
    }

       /**
     * @param reqSpec
     * @return
     */
    private RequestSpecification getRequestSpecification(RequestSpecification reqSpec) {
        if (headers != null) {
            reqSpec.headers(this.headers);
            log.info("\n Request Headers >> " + this.headers);
        }
        if (queryParams != null) {
            reqSpec.queryParams(this.queryParams);
            log.info("\n Request Query Params >> " + this.queryParams);
        }
        if (formParams != null) {
            reqSpec.formParams(this.formParams);
            log.info("\n Request Form Params >> " + this.formParams);
        }
        if (payload != null) {
            reqSpec.body(this.payload);
            log.info("\n Request Body >> " + this.payload);
        }
        if (multiPartControlName != null && multiPartObject != null) {
            reqSpec.multiPart(this.multiPartControlName, this.multiPartObject);
        }
        if (encoder != null && contentType != null) {
            reqSpec.config(new RestAssuredConfig().encoderConfig(new EncoderConfig().encodeContentTypeAs(this.contentType, this.encoder)));
        }
        reqSpec.urlEncodingEnabled(this.urlEncodingEnabled);
        return reqSpec;
    }

}
