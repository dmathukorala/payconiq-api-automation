package com.payconiq.api.requesthandler;


import com.payconiq.api.dto.response.base.BaseResponseDTO;
import com.payconiq.api.constants.RequestIdentifier;
import com.payconiq.api.dto.request.RequestParamsDTO;

import java.rmi.ServerException;

public class ServiceManager {

    private RequestCoordinator requestCoordinator;

    public BaseResponseDTO sendRequest(RequestIdentifier requestIdentifier, RequestParamsDTO requestParams) throws ServerException {
        requestCoordinator = new RequestCoordinator();
        BaseResponseDTO baseResponse = requestCoordinator.sendRequest(requestIdentifier, requestParams);
        return baseResponse;
    }

}
