package com.payconiq.api.utils.request;

import com.payconiq.api.dto.request.RequestTemplateDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryParamsProvider {

    public static Map<String, String> constructQueryParams(RequestTemplateDTO requestTemplate, Map<String, String> updateQueryParams, List<String> removeQueryParams) {

        Map<String, String> templateQueryParams = requestTemplate.getQueryParams()!=null ? requestTemplate.getQueryParams() : new HashMap<>();

        try {
            if (updateQueryParams != null && !updateQueryParams.isEmpty()) {
                for (Map.Entry<String, String> entry : updateQueryParams.entrySet()) {
                    templateQueryParams.put(entry.getKey(), entry.getValue());
                }
            }
            if (removeQueryParams != null && !removeQueryParams.isEmpty()) {
                for (String entry : removeQueryParams) {
                    templateQueryParams.remove(entry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return templateQueryParams;
    }

}
