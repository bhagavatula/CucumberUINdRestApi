package com.apitest.UIAutomation.utils;

import com.apitest.automation.utils.ThreadLocalContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class WebDriverUtil {

    public WebDriverUtil(){
    }

    public static byte[] getScreenShot(){
        byte[] screenshot = null;
        WebDriver driver = (WebDriver) ThreadLocalContext.webDriver.get();
        try{
            screenshot = (byte[]) ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        }catch(Exception var3){
            CucumberLogUtils.logError("Couldn't take screenshot");
        }
        return screenshot;
    }
}
