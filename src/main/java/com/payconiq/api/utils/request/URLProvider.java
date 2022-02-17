package com.payconiq.api.utils.request;

import com.payconiq.api.dto.request.RequestTemplateDTO;

import java.util.List;
import java.util.Map;

public class URLProvider {

    private URLProvider() {}

    public static String constructURL(RequestTemplateDTO requestTemplate, Map<String, String> addPathParams, List<String> removePathParams) {

        String relativeUri = requestTemplate.getRelativeUrl();

        try {
            if (addPathParams != null && !addPathParams.isEmpty()) {
                for (Map.Entry<String, String> entry : addPathParams.entrySet()) {
                    relativeUri = relativeUri.replace("{" + entry.getKey() + "}", entry.getValue());
                }
            }
            if (removePathParams != null && !removePathParams.isEmpty()) {
                for (String entry : removePathParams) {
                    relativeUri = relativeUri.replace("{" + entry + "}", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relativeUri;
    }

}
