package com.apitest.automation.utils;

import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LocalUtil {

    public static Properties prop = null;
    public static String FilePath = null;
    private static Properties localConf = null;

    public static final String TEST_CONFIG_FILE = "\\testConf.properties";
    private  static String RESOURCE_PATH = "src"+ File.separator+"main"+ File.separator+"resources";

    public static Properties  LoadEnvConfProps(String enviMnt) {
        if (enviMnt.contentEquals("test")){
            FilePath = getResourceDir()+TEST_CONFIG_FILE;
        }
        prop = new Properties();
        try {

            InputStream input = new FileInputStream(FilePath);
            prop.load(input);

        } catch (IOException e){
            e.printStackTrace();
        }
        return prop;
    }

    public static String getRootDir(){
        return System.getProperty("user.dir");
    }
    public static String getDataDirectory(){
        return "data";
    }
    public static String getResourceDir(){
        return getRootDir()+File.separator+RESOURCE_PATH;

    }

    public static Properties getProperties(String envName){
        if(localConf == null){
            LoadEnvConfProps(envName);
        }
        return localConf;
    }

    public static String getProperty(String key){
        String value = null;
        if(localConf == null){
            value = localConf.getProperty(key);
        }else{
            Assert.assertFalse(false,"Properties file is not loaded");
        }
        return value;
    }
    public static void setProperty(String key, String value){
        if(localConf == null){
            localConf.setProperty(key,value);
        }else{
            Assert.assertFalse(false,"Properties file is not loaded");
        }

    }

}
