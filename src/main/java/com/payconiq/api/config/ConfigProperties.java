package com.payconiq.api.config;

public class ConfigProperties {

    private ConfigProperties() {}

    private static String environment;

    public static String getEnvironment() {
        return environment;
    }

    public static void setEnvironment(String environment) {
        ConfigProperties.environment = environment;
    }
}
