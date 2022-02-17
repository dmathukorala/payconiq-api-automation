package com.payconiq.api.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {

    private Properties configFile;
    private static ConfigUtils instance;
    private static String path;

    public ConfigUtils() {

        try {
            configFile = new Properties();
            configFile.load(this.getClass().getResourceAsStream(path));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getValue(String key) {
        return configFile.getProperty(key);
    }

    public static void setConfigFilePath(String configFilePath) {
        path = configFilePath;
        instance = null;
    }

    public static String getProperty(String key) {
        if (instance == null)
            instance = new ConfigUtils();
        return instance.getValue(key);
    }

    public static String getProperty(String filePath, String fileName, String key) throws IOException {

        Properties properties = new Properties();
        File envFile = new File(filePath, fileName);
        try(FileInputStream fis = new FileInputStream(envFile)){
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(key);

    }


}