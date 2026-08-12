package utils;

import java.util.Properties;
import java.io.FileInputStream;

public class ConfigReader {
    private static final Properties properties;

    static {
        try {
            String path = "src/test/resources/config.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e) {
            throw new RuntimeException("Error -> config.properties file missing / wrong path");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}