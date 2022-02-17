package com.payconiq.api.httpmethods;

import com.github.dzieciou.testing.curl.CurlHandler;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.event.Level;


import javax.ws.rs.HttpMethod;
import java.util.*;

public class RequestHandler {
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
    private static final String STATUS_CODE_STARTS_4XX="4";
    private static final String STATUS_CODE_STARTS_5XX="5";
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

    private void customCurlHandling(){
        CurlHandler handler = (curl, options) -> {
            Options.builder().useLogLevel(Level.INFO).build();
            curls.add(curl);
        };
        config = CurlRestAssuredConfigFactory.createConfig(Collections.singletonList(handler));
    }

    private Response httpRequests(String request){
        log.info("Get URL --> " + url);
        RequestSpecification spec = getRequestSpecification(RestAssured.given());
        customCurlHandling();
        Response res;
        RequestSpecification requestSpecification = spec.config(config).when();
        switch (request.toUpperCase()){
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
            case "PATCH":
                res = requestSpecification.patch(this.url);
                break;
            default:
                throw new IllegalArgumentException("Invalid http request");
        }
        Response response = res.then().extract().response();
        log.info(curls.get(0));
       // curlReporting(String.valueOf(res.getStatusCode()), response);
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
        return httpRequests("PATCH");
    }

    /**
    /**
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

    public int getStatusCode() {
        int statusCode = 0;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            statusCode = spec.config(config).when().get(this.url).getStatusCode();
            log.info("Status code --> " + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCode;
    }


    public String getStatusLine() {
        String statusLine = null;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            statusLine = spec.config(config).when().get(this.url).getStatusLine();
            log.info("Status Line --> " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusLine;
    }


    public Map<String, String> getHeaders() {
        Map<String, String> responseHeaders = new HashMap<>();
        Headers allHeaders;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            allHeaders = spec.config(config).when().get(this.url).getHeaders();

            for (Header header : allHeaders) {
                responseHeaders.put(header.getName(), header.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseHeaders;
    }


    public long getResponseTime() {
        long responseTime = 0;
        log.info("Get URL --> " + url);
        try {
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            responseTime = spec.config(config).when().get(this.url).getTime();
            log.info("Response Time --> " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseTime;
    }

    public int postStatusCode() {
        int statusCode = 0;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            statusCode = spec.config(config).when().post(this.url).getStatusCode();
            log.info("Status code --> " + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCode;
    }


    public String postStatusLine() {
        String statusLine = null;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            statusLine = spec.config(config).when().post(this.url).getStatusLine();
            log.info("Status Line --> " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusLine;
    }


    public Map<String, String> postHeaders() {
        Map<String, String> responseHeaders = new HashMap<>();
        Headers allHeaders;
        RestAssured.urlEncodingEnabled = false;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            allHeaders = spec.config(config).when().post(this.url).getHeaders();

            for (Header header : allHeaders) {
                responseHeaders.put(header.getName(), header.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseHeaders;
    }


    public long postResponseTime() {
        long responseTime = 0;
        log.info("Get URL --> " + url);
        try {
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            responseTime = spec.config(config).when().post(this.url).getTime();
            log.info("Response Time --> " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseTime;
    }

    public int putStatusCode() {
        int statusCode = 0;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            statusCode = spec.config(config).when().put(this.url).getStatusCode();
            log.info("Status code --> " + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCode;
    }


    public String putStatusLine() {
        String statusLine = null;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            statusLine = spec.config(config).when().put(this.url).getStatusLine();
            log.info("Status Line --> " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusLine;
    }


    public Map<String, String> putHeaders() {
        Map<String, String> responseHeaders = new HashMap<>();
        Headers allHeaders;
        RestAssured.urlEncodingEnabled = false;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            allHeaders = spec.config(config).when().put(this.url).getHeaders();

            for (Header header : allHeaders) {
                responseHeaders.put(header.getName(), header.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseHeaders;
    }


    public long putResponseTime() {
        long responseTime = 0;
        log.info("Get URL --> " + url);
        try {
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            responseTime = spec.config(config).when().put(this.url).getTime();
            log.info("Response Time --> " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseTime;
    }

    public int deleteStatusCode() {
        int statusCode = 0;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            statusCode = spec.config(config).when().delete(this.url).getStatusCode();
            log.info("Status code --> " + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCode;
    }


    public String deleteStatusLine() {
        String statusLine = null;
        try {
            log.info("Get URL --> " + url);
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            statusLine = spec.config(config).when().delete(this.url).getStatusLine();
            log.info("Status Line --> " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusLine;
    }


    public long deleteResponseTime() {
        long responseTime = 0;
        log.info("Get URL --> " + url);
        try {
            RequestSpecification spec = getRequestSpecification(RestAssured.given());
            responseTime = spec.config(config).when().delete(this.url).getTime();
            log.info("Response Time --> " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseTime;
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
        if(multiPartControlName != null && multiPartObject !=null){
            reqSpec.multiPart(this.multiPartControlName, this.multiPartObject);
        }
        if(encoder !=null && contentType!=null){
            reqSpec.config(new RestAssuredConfig().encoderConfig(new EncoderConfig().encodeContentTypeAs(this.contentType, this.encoder)));
        }
        reqSpec.urlEncodingEnabled(this.urlEncodingEnabled);
        return reqSpec;
    }

    private void curlReporting(String statusCode, Response response){
        if(statusCode.startsWith(STATUS_CODE_STARTS_4XX)||(statusCode.startsWith(STATUS_CODE_STARTS_5XX))){
            Allure.addAttachment("curl logger",curls.get(0));
            Allure.addAttachment("response body",response.body().print());
            Allure.addAttachment("response headers",response.headers().toString());
        }
    }
}
