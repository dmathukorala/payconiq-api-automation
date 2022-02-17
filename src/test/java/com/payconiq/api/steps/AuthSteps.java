package com.payconiq.api.steps;


import com.payconiq.api.base.ServiceBase;
import com.payconiq.api.constants.RequestIdentifier;
import com.payconiq.api.dto.request.RequestParamsDTO;
import com.payconiq.api.dto.response.base.BaseResponseDTO;
import com.payconiq.api.dto.response.auth.CreateTokenDTO;
import com.payconiq.api.httpmethods.RequestHandler;
import com.payconiq.api.requesthandler.ServiceManager;
import io.qameta.allure.Step;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AuthSteps extends ServiceBase {

    private ServiceManager serviceManager;
    private Log log = LogFactory.getLog(RequestHandler.class);

    public AuthSteps() {
        serviceManager = new ServiceManager();
    }

    @Step("Create new booking")
    public CreateTokenDTO createAuthToken(RequestParamsDTO requestParams) {
        CreateTokenDTO createTokenDTO = null;
        try {
            BaseResponseDTO baseResponseDTO = serviceManager.sendRequest(RequestIdentifier.auth_CreateToken, requestParams);

            createTokenDTO = (CreateTokenDTO) getResponseDTO(baseResponseDTO.getResponse(), CreateTokenDTO.class);
            createTokenDTO.setResponse(baseResponseDTO.getResponse());

        } catch (Exception e) {
            log.error(e);
        }
        return createTokenDTO;
    }
}
