package com.orangehrmlive.opensource.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private static ConfigUtils configUtils;
    private final Properties prop;

    private ConfigUtils(){
        prop = readProp();
    }

    public static ConfigUtils getInstance(){
        if(configUtils == null){
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    public Properties readProp() {
        Properties prop = new Properties();
        try (InputStream is = new FileInputStream("src/test/resources/credentials/user.properties")) {
            prop.load(is);
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);// Terminate
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return prop;
    }

    public String getFirstName(){
        return (String) prop.get("userName");
    }

    public String getPassword(){
        return (String) prop.get("password");
    }
    public String getBaseURL(){
        return (String) prop.get("URL");
    }
}
