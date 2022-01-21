package com.apitest.UIAutomation.pages.login;

import com.apitest.automation.utils.ThreadLocalContext;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CustomSystemSetuup {

    public static final String IE= "ie";
    public static final String FIREFOX= "firefox";
    public static final String CHROME= "chrome";
    public static final String BROWSER= "browser";
    public static final String DRIVER_BASE_PATH= "driverBasePath";
    public static final String CHROME_OPTIONS= "chromeoptions.args";
    public static final String CHROME_OPTIONS_VALUE= "--start.maximized";
    public CustomSystemSetuup(){

    }
    public static void webDriver(){
        String browser = System.getProperty("browser");
        String driverFolder = System.getProperty("driverBasePath");
        createDownloadDirectoryScenario();
        String downloadFilepath= getScenarioDownLoadPath();
        byte var5 = -1;
        switch (browser.hashCode()){
            case -1361128838:
                if(browser.equals("chrome")){
                    var5 = 1;
                }
                break;
            case -849452327:
                if(browser.equals("firefox")){
                    var5 = 3;
                }
            case -554494698:
                if(browser.equals("phantomjs")){
                    var5 = 3;
                }
                break;
            case 3356:
                if (browser.equals("ie")){
                    var5 = 2;
                }
        }

        String driverPath;
        switch (var5){
            case 0:
                driverPath = driverFolder+File.separator+"GekoDriver"+File.separator+"geckodriver.exe";
                System.setProperty("webdriver.gecko.driver", driverPath);
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList",2);
                profile.setPreference("browser.download.dir",downloadFilepath);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/html,application/json,application/octet-stream doc xls xlsx pdf txt");
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                profile.setPreference("browser.download.manager.closeWhenDone", true);
                profile.setPreference("browser.download.manager.useWindow", false);
                profile.setPreference("browser.download.panel.shown", false);
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                profile.setPreference("pdfjs.disabled", true);
                FirefoxOptions option = new FirefoxOptions();
                option.setProfile(profile);
                WebDriverRunner.setWebDriver((new FirefoxDriver(option)));
                WebDriverRunner.getWebDriver().manage().window().maximize();
                break;
            case 1:
                driverPath = driverFolder+File.separator+"ChromeDriver"+File.separator+"chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", driverPath);
                System.setProperty("chromeoptions.args", "--start-maxmized");
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.password_manager_enabled",false);
                chromePrefs.put("profile.default_content_setting.popups",2);
                chromePrefs.put("download.default_directory",downloadFilepath);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs",chromeOptions);
                chromeOptions.addArguments("incognito");
                WebDriverRunner.setWebDriver(new ChromeDriver(chromeOptions));
                WebDriverRunner.getWebDriver().manage().window().maximize();
                break;
            case 2:
                driverPath = driverFolder+File.separator+"IEDRIVER"+File.separator+"IEDriverServer.exe";
                System.setProperty("webdriver.ie.driver", driverPath);
                InternetExplorerOptions options = (new InternetExplorerOptions()).ignoreZoomSettings().introduceFlakinessByIgnoringSecurityDomains();
                options.setCapability("enablePersistentHover",false);
                options.setCapability("enableNativeElements",false);
                options.setCapability("requireWindowFocus",true);
                Configuration.startMaximized = true;
                WebDriver driver = new InternetExplorerDriver(options);
                driver.manage().window().maximize();
                WebDriverRunner.setWebDriver(driver);
                break;
            case 3:
                System.setProperty("panthomjs.binary.path","C:\\Developers\\node_modules\\panthomjs-prebuilt\\lib\\panthom\\bin\\panthomjs.exe");
                break;
            default:
                Assert.fail("Invalid Browser Set, allowble topions are chrome, firefox, ie and panthom but you specified"+ browser);
        }
    }

    public static void systemProperties(){
        Properties properties = System.getProperties();
        Yaml yaml = new Yaml();
        try{
            InputStream inputStream = CustomSystemSetuup.class.getClassLoader().getResourceAsStream("application.ymal");
            Throwable var3 = null;
            try{
                Map<String, Object> obj = (Map)yaml.load(inputStream);
                if(properties.getProperty("browse") == null){
                    System.setProperty("browser", (String)obj.get("browser"));
                }
                if(properties.getProperty("driverBasePath") == null){
                    System.setProperty("driverBasePath", (String)obj.get("driverBasePath"));
                }
            }catch(Throwable var13){
                var3 = var13;
                throw var13;
            }
            finally {
                if(inputStream != null){
                    if(var3!=null){
                        try{
                            inputStream.close();
                        }catch (Throwable var12){
                            var3.addSuppressed(var12);
                        }
                    }else{
                        inputStream.close();
                    }
                }
            }
        } catch(IOException var15){
            Assert.fail("Input Stream Closed Unexpectedly");
        }
    }

    public static void selenideProperties(){
        Properties properties = System.getProperties();
        if(properties.getProperty("chrome.args") == null){
            System.setProperty("chromeoptions.args","--start-maximized");
        }
        System.setProperty("webdriver.chrome.verboseLogging","true");
        System.setProperty("-Dselenide.fileDownload","PROXY");
        System.setProperty("-Dselenide.timeout","10000");

    }
    public static void initiateDriver(){
        systemProperties();
        webDriver();
        selenideProperties();
    }

    public static  String getScenarioDownLoadPath(){
        return ThreadLocalContext.getMapData() != null ? System.getProperty("user.dir")+ File.separator+"DownLoads"+File.separator+ThreadLocalContext.getMapData().getCurrentScenario() : null;
    }

    public static void createDownloadDirectoryScenario(){
        if(ThreadLocalContext.getMapData()!= null){
            File newDirectory = new File("Downloads"+ File.separator+ThreadLocalContext.getMapData().getCurrentScenario());
            newDirectory.mkdir();
        }
    }


}


