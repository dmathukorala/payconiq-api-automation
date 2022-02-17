package com.payconiq.api.utils.common;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.List;
import java.util.Map;

public abstract class FilterRequestResponse {
    private static Log log = LogFactory.getLog(FilterRequestResponse.class);

    protected FilterRequestResponse() {}

    /**
     * Return elements from given jsonpath. Please follow jsonpath syntax when sending values for jasonroot
     *
     * @param response
     * @param jsonPath
     * @return
     */
    public static Object getJsonElements(String response, String jsonPath) {
        Object elements = null;
        try {
            elements = JsonPath.read(response, jsonPath);
            log.info("Get JSON element/s: Path --> " + jsonPath + " : Value --> " + elements);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return elements;
    }

    /**
     * @param payload
     * @param elementPath
     * @param updateValue
     * @return
     */
    public static String updateJsonElement(String payload, String elementPath, String updateValue) {
        String constructPayoad = "";
        try {
            DocumentContext updatedJson = JsonPath.parse(payload).set(elementPath, updateValue);
            constructPayoad = updatedJson.jsonString();
            log.info("JSON payload after update element  --> " + constructPayoad);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return constructPayoad;
    }

    /**
     * @param payload
     * @param updateValues
     * @return
     */
    public static String updateMultipleJsonElements(String payload, Map<String, Object> updateValues) {
        String constructPayoad = "";
        DocumentContext updatedJson = null;

        try {
            updatedJson = JsonPath.parse(payload);
            for (String element : updateValues.keySet()) {
                updatedJson = updatedJson.set(element, updateValues.get(element));
            }
            constructPayoad = updatedJson.jsonString();
            log.info("JSON payload after update elements  --> " + constructPayoad);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return constructPayoad;
    }

    /**
     * @param payload
     * @param deleteElement
     * @return
     */
    public static String removeJsonElement(String payload, String deleteElement) {
        String constructPayoad = "";
        try {
            DocumentContext updatedJson = JsonPath.parse(payload).delete(deleteElement);
            constructPayoad = updatedJson.jsonString();
            log.info("JSON payload after remove element  --> " + constructPayoad);

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return constructPayoad;
    }

    /**
     * @param payload
     * @param deleteElements
     * @return
     */
    public static String removeMultipleJsonElements(String payload, List<String> deleteElements) {
        String constructPayoad = "";
        DocumentContext updatedJson = null;
        try {
            updatedJson = JsonPath.parse(payload);
            for (String element : deleteElements) {
                updatedJson = updatedJson.delete(element);
            }
            constructPayoad = updatedJson.jsonString();
            if (constructPayoad.contains("{}"))
                constructPayoad = null;
            log.info("JSON payload after remove elements  --> " + constructPayoad);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return constructPayoad;
    }


}
