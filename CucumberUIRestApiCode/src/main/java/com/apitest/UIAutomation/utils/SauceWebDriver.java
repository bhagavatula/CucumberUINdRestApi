package com.apitest.UIAutomation.utils;

import com.apitest.automation.utils.LocalUtil;
import com.apitest.automation.utils.ThreadLocalContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

public class SauceWebDriver {
    private static final String buildID = LocalUtil.getProperty("SauceBuildID");
    private static final String browser = LocalUtil.getProperty("browser");
    private static final String version = LocalUtil.getProperty("version");
    private static final String os = LocalUtil.getProperty("sauceBuildID");
    private static final String username = LocalUtil.getProperty("sauceUsername");
    private static final String accesskey = LocalUtil.getProperty("sauceKey");
    private static final String tunnelID = LocalUtil.getProperty("sauceTunnel");
    private static final String parentTunnelID = LocalUtil.getProperty("parentSauceTunnel");
    private static final String proxyHost = LocalUtil.getProperty("proxyHost");
    private static final String proxyPort = LocalUtil.getProperty("proxyPort");
    private static final String resolution = "1280x1024";
    private static final String idleTimeout = "240";

    public static WebDriver getWebDriver() throws IOException{
        DesiredCapabilities caps = new DesiredCapabilities();
        if(browser != null) caps.setCapability(CapabilityType.BROWSER_NAME,browser);
        if(version != null) caps.setCapability(CapabilityType.VERSION,version);
        if(os != null) caps.setCapability(CapabilityType.PLATFORM,os);
        caps.setCapability("username",username);
        caps.setCapability("tunnel-identifier",tunnelID);
        caps.setCapability("parent-tunnel",parentTunnelID);
        caps.setCapability("buildID",buildID);
        caps.setCapability("name", ThreadLocalContext.getScenarioName());
        caps.setCapability("extendedDebugging",true);
        caps.setCapability("screenResolution",resolution);
        caps.setCapability("idletimeout",idleTimeout);
        caps.acceptInsecureCerts();
        System.setProperty("http.proxyHost",proxyHost);
        System.setProperty("http.proxyPort",proxyPort);
        WebDriver driver = new RemoteWebDriver(new URL("http://odemand.saucelabs.com:80/wd/hub"),caps);
        ThreadLocalContext.sauceSessionId.set(((((RemoteWebDriver) driver).getSessionId()).toString()));
        return driver;

    }


}
