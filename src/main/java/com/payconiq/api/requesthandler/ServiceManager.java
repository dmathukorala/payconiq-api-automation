package com.payconiq.api.requesthandler;


import com.payconiq.api.dto.response.base.BaseResponseDTO;
import com.payconiq.api.dto.request.RequestParamsDTO;

public class ServiceManager {

    public BaseResponseDTO sendRequest(String requestIdentifier, RequestParamsDTO requestParams) {
        RequestCoordinator requestCoordinator = new RequestCoordinator();
        return requestCoordinator.sendRequest(requestIdentifier, requestParams);
    }

}
