package com.payconiq.api.requesthandler;

import com.payconiq.api.dto.request.RequestParamsDTO;
import com.payconiq.api.dto.request.RequestTemplateDTO;
import com.payconiq.api.dto.response.base.BaseResponseDTO;
import com.payconiq.api.httpmethods.RequestBuilder;
import com.payconiq.api.httpmethods.RequestHandler;
import com.payconiq.api.utils.request.*;
import io.restassured.response.Response;

import java.util.Map;

public class RequestCoordinator {

    private String baseURI;
    private String relativeUri;
    private Map<String, String> headers;
    private String requestPayload;
    private Map<String, String> queryParams;
    private Map<String, String> formParams;
    private String multiPartControlName;
    private Object multiPartObject;

    /**
     * Construct the payload and send request
     *
     * @param requestIdentifier
     * @param requestParams
     * @return
     */
    public BaseResponseDTO sendRequest(String requestIdentifier, RequestParamsDTO requestParams) {

        RequestTemplateDTO requestTemplate = RequestUtils.generateRequestTemplate(requestIdentifier);
        baseURI = requestTemplate.getBaseUrl();
        relativeUri = URLProvider.constructURL(requestTemplate, requestParams.getUpdatePathParams(), requestParams.getRemovePathParams());
        headers = HeaderProvider.constructHeaders(requestTemplate, requestParams.getUpdateHeaders(), requestParams.getRemoveHeaders());
        queryParams = QueryParamsProvider.constructQueryParams(requestTemplate, requestParams.getUpdateQueryParams(), requestParams.getRemoveQueryParams());
        formParams = FormParamsProvider.constructFormParams(requestTemplate, requestParams.getUpdateFormParams(), requestParams.getRemoveFormParams());
        multiPartControlName = MultipartDataProvider.constructMultipartControlName(requestTemplate, requestParams.getUpdateMultiPartControlName(), requestParams.getRemoveMultiPartControlName());
        multiPartObject = MultipartDataProvider.constructMultipartObject(requestTemplate, requestParams.getUpdateMultiPartObject(), requestParams.getRemoveMultiPartObject());

        if (requestParams.getBody() != null && !requestParams.getBody().isEmpty())
            requestPayload = requestParams.getBody().toJSONString();
        else if (requestParams.getUpdatePayload() != null || requestParams.getRemovePayload() != null)
            requestPayload = PayloadProvider.constructPayload(requestTemplate, requestParams.getUpdatePayload(), requestParams.getRemovePayload());
        else if (requestTemplate.getPayload() != null)
            requestPayload = requestTemplate.getPayload().toString();

        RequestHandler buildRequest = setupRequestHandler(requestParams);

        return sendRequest(requestTemplate, buildRequest);
    }

    private BaseResponseDTO sendRequest(RequestTemplateDTO requestTemplate, RequestHandler buildRequest) {

        BaseResponseDTO baseResponse = new BaseResponseDTO();
        Response response;

        switch (requestTemplate.getMethod()) {
            case "GET":
                response = buildRequest.getResponse();
                break;
            case "POST":
                response = buildRequest.postResponse();
                break;
            case "PUT":
                response = buildRequest.putResponse();
                break;
            case "DELETE":
                response = buildRequest.deleteResponse();
                break;
            case "PATCH":
                response = buildRequest.patchResponse();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestTemplate.getMethod());
        }

        baseResponse.setResponse(response);

        return baseResponse;
    }

    private RequestHandler setupRequestHandler(RequestParamsDTO requestParams) {

        RequestBuilder requestBuilder;

        requestBuilder = new RequestBuilder();

        requestBuilder.setUrl(baseURI + relativeUri);

        if (headers != null && !headers.isEmpty())
            requestBuilder.setHeaders(headers);

        if (requestPayload != null && !requestPayload.isEmpty())
            requestBuilder.setPayload(requestPayload);

        if (queryParams != null && !queryParams.isEmpty())
            requestBuilder.setQueryParams(queryParams);

        if (formParams != null && !formParams.isEmpty())
            requestBuilder.setFormParams(formParams);

        if (multiPartObject != null && multiPartControlName != null) {
            requestBuilder.setMultiPartControlName(requestParams.getUpdateMultiPartControlName());
            requestBuilder.setMultiPartObject(requestParams.getUpdateMultiPartObject());
        }

        if (requestParams.getUpdateEncoder() != null && requestParams.getUpdateEncoderContentType() != null) {
            requestBuilder.setUpdateEncoder(requestParams.getUpdateEncoder());
            requestBuilder.setUpdateEncoderContentType(requestParams.getUpdateEncoderContentType());
        }

        requestBuilder.setUrlEncodingEnabled(requestParams.isUrlEncodingEnabled());

        return requestBuilder.build();
    }
}
