package com.apitest.UIAutomation.utils;

import com.apitest.automation.utils.LocalUtil;

import java.io.File;

public class EnvUtil {
    private static XMLUtils getEnv(){
        String env =  LocalUtil.getProperty("env");
        String envFilePath = LocalUtil.getResourceDir()+ File.separator+"env"+File.separator+env+".xml";
        File envFile = new File(envFilePath);
        XMLUtils xml = new XMLUtils(envFile);
        return xml;
    }
    public static String getAutomationUrl(){
        String ret = "";
        XMLUtils xml = getEnv();
        ret = xml.get_data(("//application[@id='AUTOMATIONURL']/url"));
        return ret;
    }
}
