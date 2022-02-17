package com.payconiq.api.utils.request;

import com.payconiq.api.config.ConfigParams;
import com.payconiq.api.config.ConfigUtils;
import com.payconiq.api.constants.RequestIdentifier;
import com.payconiq.api.dto.request.RequestTemplateDTO;
import org.json.simple.JSONObject;

import java.util.Map;

public class RequestUtils {

    public static RequestTemplateDTO generateRequestTemplate(RequestIdentifier requestIdentifier) {

        JSONObject requestTemplate;
        RequestTemplateDTO requestTemplateDTO = new RequestTemplateDTO();
        String[] identifier = requestIdentifier.toString().split("_");
        String serviceName = identifier[0];
        String apiName = identifier[1];

        try {
            requestTemplate = PayloadProvider.getJSONBodyTemplate(ConfigParams.SERVICE_DATA_PATH + "/" + serviceName, apiName.concat(".json"));
            requestTemplateDTO.setServiceName(serviceName);
            requestTemplateDTO.setApiName(apiName);
            requestTemplateDTO.setBaseUrl(ConfigUtils.getProperty(requestTemplate.get("baseUrl").toString()));
            requestTemplateDTO.setRelativeUrl(requestTemplate.get("relativeUrl").toString());
            requestTemplateDTO.setHeaders((Map<String, String>) requestTemplate.get("headers"));
            requestTemplateDTO.setQueryParams((Map<String, String>) requestTemplate.get("queryParams"));
            requestTemplateDTO.setFormParams((Map<String, String>) requestTemplate.get("formParams"));
            requestTemplateDTO.setPayload(requestTemplate.get("payload"));
            requestTemplateDTO.setMethod(requestTemplate.get("method").toString());
            requestTemplateDTO.setStatus(requestTemplate.get("status").toString());
            requestTemplateDTO.setMultiPartControlName((String) requestTemplate.get("multiPartControlName"));
            requestTemplateDTO.setMultiPartObject(requestTemplate.get("multiPartObject"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestTemplateDTO;
    }

}
