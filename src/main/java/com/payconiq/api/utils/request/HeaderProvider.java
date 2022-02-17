package com.payconiq.api.utils.request;

import com.payconiq.api.dto.request.RequestTemplateDTO;

import java.util.List;
import java.util.Map;

public class HeaderProvider {

    public static Map<String, String> constructHeaders(RequestTemplateDTO requestTemplate, Map<String, String> updateHeadersValues, List<String> removeHeaders) {

        Map<String, String> templateHeaders = requestTemplate.getHeaders();

        try {
            if (updateHeadersValues != null && !updateHeadersValues.isEmpty()) {

                for (Map.Entry<String, String> entry : updateHeadersValues.entrySet()) {
                    templateHeaders.put(entry.getKey(), entry.getValue());
                }
            }
            if (removeHeaders != null && !removeHeaders.isEmpty()) {
                for (String entry : removeHeaders) {
                    templateHeaders.remove(entry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return templateHeaders;
    }

}
