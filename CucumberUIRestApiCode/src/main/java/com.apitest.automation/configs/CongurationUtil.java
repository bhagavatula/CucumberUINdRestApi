package com.apitest.automation.configs;

import com.apitest.automation.utils.LocalUtil;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class CongurationUtil {

    public static Properties allpros;
//    public static final String TEST_CONFIG_FILE = "/Conf/testConf.properties";
//    private  static String RESOURCE_PATH = "src"+ File.separator+"main"+ File.separator+"resources";

    public static String getGlobalProperty(String key, String Environment) throws IOException {
        try {
            if (allpros == null) {
                allpros = LocalUtil.LoadEnvConfProps(Environment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key != null ? allpros.getProperty(key) : null;
    }
    public static String getBaseURL() throws IOException{
        return getGlobalProperty("baseURL","test");
    }



}
