package com.payconiq.api.utils.request;

import com.payconiq.api.dto.request.RequestTemplateDTO;

public class MultipartDataProvider {

    private MultipartDataProvider() {
    }

    public static String constructMultipartControlName(RequestTemplateDTO requestTemplate, String updateMultipartControlName, String removeMultipartControlName) {

        String multipartControlName = requestTemplate.getMultiPartControlName() != null ? requestTemplate.getMultiPartControlName() : null;

        try {
            if (updateMultipartControlName != null) {
                multipartControlName = updateMultipartControlName;
            }
            if (multipartControlName != null && removeMultipartControlName != null) {
                multipartControlName = multipartControlName.replace(removeMultipartControlName, "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return multipartControlName;
    }

    public static Object constructMultipartObject(RequestTemplateDTO requestTemplate, Object updateMultipartObject, Object removeMultipartObject) {

        Object multiPartObject = requestTemplate.getMultiPartObject() != null ? requestTemplate.getMultiPartObject() : null;

        try {
            if (updateMultipartObject != null) {
                multiPartObject = updateMultipartObject;
            }
            if (multiPartObject != null && removeMultipartObject != null && multiPartObject.equals(removeMultipartObject)) {
                multiPartObject = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return multiPartObject;
    }

}
