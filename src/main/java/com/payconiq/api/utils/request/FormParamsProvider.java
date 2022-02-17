package com.payconiq.api.utils.request;

import com.payconiq.api.dto.request.RequestTemplateDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormParamsProvider {

    public static Map<String, String> constructFormParams(RequestTemplateDTO requestTemplate, Map<String, String> updateFormParams, List<String> removeFormParams) {

        Map<String, String> templateFormParams = requestTemplate.getFormParams()!=null ? requestTemplate.getFormParams() : new HashMap<>();

        try {
            if (updateFormParams != null && !updateFormParams.isEmpty()) {
                for (Map.Entry<String, String> entry : updateFormParams.entrySet()) {
                    templateFormParams.put(entry.getKey(), entry.getValue());
                }
            }
            if (removeFormParams != null && !removeFormParams.isEmpty()) {
                for (String entry : removeFormParams) {
                    templateFormParams.remove(entry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return templateFormParams;
    }

}
