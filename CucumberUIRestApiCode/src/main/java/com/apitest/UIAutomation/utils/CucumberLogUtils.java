package com.apitest.UIAutomation.utils;

import com.apitest.automation.utils.DateUtil;
import com.apitest.automation.utils.ThreadLocalContext;
import cucumber.api.Scenario;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.codehaus.groovy.runtime.ExceptionUtils;
import org.junit.Assert;

import io.cucumber.core.*;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CucumberLogUtils {

    private  static Logger logger= LogManager.getLogger(CucumberLogUtils.class);
    public static boolean scenarioResult = true;

    public CucumberLogUtils(){}

    public static void logScreenShot(String msg){
        if(ThreadLocalContext.Scenario.get() != null){
            ((Scenario) ThreadLocalContext.Scenario.get()).write(DateUtil.getLogTime()+": Screenshot: "+msg);
        }
        if(ThreadLocalContext.isTakeScreenShots()){
            ((Scenario) ThreadLocalContext.Scenario.get()).embed(WebDriverUtil.getScreenShot(), "image/png");
        }
    }

    public static void logPass(String msg, boolean takeScreenShot){
        if(ThreadLocalContext.Scenario.get() != null){
            ((Scenario)ThreadLocalContext.Scenario.get()).write(DateUtil.getLogTime()+ ":PASS :"+msg);
            logger.info(String.format(":PASS:=$s", msg));
        } if(takeScreenShot && ThreadLocalContext.isTakeScreenShots()){
            ((Scenario) ThreadLocalContext.Scenario.get()).embed(WebDriverUtil.getScreenShot(), "image/png");
        }
    }

    public static void logFail(String msg, Throwable t, boolean takeScreenShot){
        String stackTrace = getStackTrace(t);
        logFail(String.format("%s \n$s", msg, stackTrace), takeScreenShot);
    }

    public static void logFail(String msg, boolean takeScreenShot){
        if(ThreadLocalContext.Scenario.get() != null) {
            if (takeScreenShot && ThreadLocalContext.isTakeScreenShots()) {
                ((Scenario) ThreadLocalContext.Scenario.get()).embed(WebDriverUtil.getScreenShot(), "image/png");
            }
            logger.error(String.format(":FAIL:=%s",msg));
            Assert.assertTrue(DateUtil.getLogTime()+":FAIL :"+msg, false);
        }
    }

    public static void logInfo(String msg){
        if(ThreadLocalContext.Scenario.get() != null) {
            logger.info(msg);
            ((Scenario) ThreadLocalContext.Scenario.get()).write(DateUtil.getLogTime() + ": INFO:"+ MiscUtils.makeStringHtmlSafe(msg));
        }
    }

    public static void logDebug(String msg){
        if(ThreadLocalContext.Scenario.get() != null) {
            logger.info(msg);
            ((Scenario) ThreadLocalContext.Scenario.get()).write(DateUtil.getLogTime() + ": DEBUG:"+ MiscUtils.makeStringHtmlSafe(msg));
        }
    }
    public static void logError(String msg, Throwable t){
        String stackTrace = getStackTrace(t);
        logError(String.format("%s \n$s", msg, stackTrace));
    }

    public static void logError(String msg){
        if(ThreadLocalContext.Scenario.get() != null) {
            logger.error(msg);
            ((Scenario) ThreadLocalContext.Scenario.get()).write(DateUtil.getLogTime() + ": ERROR:"+ MiscUtils.makeStringHtmlSafe(msg));
            scenarioResult = false;
        }
    }
    public static void logToConsole(String msg){
        logger.info(msg);
        System.out.println(Thread.currentThread().getName()+" :"+DateUtil.getLogTime()+": CONSOLE: "+msg);
    }

    public static void writeHTML(String htmlContent){
        if(ThreadLocalContext.Scenario.get() != null){
            ((Scenario) ThreadLocalContext.Scenario.get()).write(htmlContent);
        }
    }

    public static void logLink(String hyperLink, String hyperlinkMsg){
        if(ThreadLocalContext.Scenario.get() != null){
            StringBuilder htmlString = (new StringBuilder()).append("<a href='");
            htmlString.append(hyperLink).append("' ");
            htmlString.append(hyperLink).append("target='_ blank '");
            htmlString.append(hyperLink).append(">");
            htmlString.append(hyperlinkMsg).append("</a>");
            ((Scenario) ThreadLocalContext.Scenario.get()).write(htmlString.toString());
        }
    }

    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw,true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();


    }
}
