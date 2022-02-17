package com.payconiq.api.utils.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.api.dto.request.RequestTemplateDTO;
import com.payconiq.api.utils.common.FilterRequestResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PayloadProvider {


    public static JSONObject getJSONBodyTemplate(String templateBodyPath, String filename) throws Exception {
            File file = new File(templateBodyPath, filename);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(new FileReader(file));
    }

    public static String constructPayload(RequestTemplateDTO requestTemplate, Map<String, Object> updatePayload, List<String> removePayload) {

        String updatedPayload = null;
        ObjectMapper mapper = new ObjectMapper();

        if (requestTemplate.getPayload() != null) {
            try {
                updatedPayload = mapper.writeValueAsString(requestTemplate.getPayload());

                if (updatePayload != null && !updatePayload.isEmpty()) {
                    updatedPayload = FilterRequestResponse.updateMultipleJsonElements(updatedPayload, updatePayload);
                }
                if (removePayload != null && !removePayload.isEmpty()) {
                    updatedPayload = FilterRequestResponse.removeMultipleJsonElements(updatedPayload, (ArrayList<String>) removePayload);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return updatedPayload;
    }

}
