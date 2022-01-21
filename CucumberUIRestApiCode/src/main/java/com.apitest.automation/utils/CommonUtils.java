package com.apitest.automation.utils;

import com.apitest.UIAutomation.pages.wrappers.WebDriverWrapper;
import cucumber.api.Scenario;
import groovy.json.StringEscapeUtils;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.springframework.stereotype.Component;

import javax.naming.Reference;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class CommonUtils {
    static Logger logger = Logger.getLogger(String.valueOf(CommonUtils.class));
    private static WebDriverWrapper webDriverWrapper;

    public CommonUtils(){

    }

    public static ArrayList<String> addParams(ArrayList<String> list, String addMe){
        list.add(addMe);
        return list;
    }

    public static String constructPageLink(ArrayList<String> list){
        StringBuffer temp = new StringBuffer();
        Iterator var2 = list.iterator();
        while(var2.hasNext()){
            String param = (String)var2.next();
            if(param != null){
                temp.append(param);
                temp.append("/");
            }
        }
        return temp.toString();
    }


    public static  void sleep(long millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException var3){
            logger.info("Exception encountered in sleep():\n"+var3);
        }
    }

    public static void closeQuietly(Closeable...closeable){
        Closeable[] var1 = closeable;
        int var2 = closeable.length;
        for(int var3=0; var3 < var2; ++var3){
            Closeable obj2Close = var1[var3];
            if(obj2Close != null){
                try {
                    obj2Close.close();
                }catch (IOException var6){
                    logger.info("Exception encountered while closing the stream:\n"+ var6);
                }
            }
        }
    }

    public static void logInfo(String msg, boolean logMessage, boolean captureScreen){
        logger.info("::"+ msg);
        attachEvidence(msg,logMessage,captureScreen,": INFO:");
    }

    public static void logError(String msg, boolean logMessage, boolean captureScreen){
        logger.info("::"+ msg);
        attachEvidence(msg,logMessage,captureScreen,": INFO:");
    }

    public static void attachEvidence(String msg, boolean logMessage, boolean captureScreen, String logLevel){
        Scenario scenario = ThreadLocalContext.getScenarioDetails();
        if(logMessage){
            scenario.write(DateUtil.getLogTime()+logLevel+makeStringHtmlSafe(msg));
        }
        if(captureScreen){
            scenario.embed(getWebDriverWrapper().logScreenshot(), "image/png");
        }
    }

    public static void threadLocalCleanUp(){
        try{
            Thread thread = Thread.currentThread();
            Field threadLocalField = Thread.class.getDeclaredField("threadLocals");
            threadLocalField.setAccessible(true);
            Object threadLocalTable = threadLocalField.get(thread);
            Class threadLocalMapClass  = Class.forName("java.land.ThreadLocal$ThreadLocalMap");
            Field tableField = threadLocalMapClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object table = tableField.get(threadLocalTable);
            Field referemtField = Reference.class.getDeclaredField("referent");
            referemtField.setAccessible(true);

            for(int i = 0 ; i< Array.getLength(table); i++){
                Object entry = Array.get(table,i);
                if(entry != null){
                    ThreadLocal threadLocal = (ThreadLocal) referemtField.get(entry);
                    threadLocal.remove();
                }
            }
        }catch(Exception var10){
            throw new IllegalStateException(var10);
        }
    }

    public static ArrayList<Map<String,?>> reponsePraser(Response response, String endPoint){
        ArrayList<Map<String,?>> list = (ArrayList)((Response)((ValidatableResponse)response.then()).extract().response().path(endPoint, new String[0]));
        return list;
    }

    public static String makeStringHtmlSafe(String str){
        return StringEscapeUtils.escapeJava(str);
    }

    public static WebDriverWrapper getWebDriverWrapper(){
        return new WebDriverWrapper();
    }

}
