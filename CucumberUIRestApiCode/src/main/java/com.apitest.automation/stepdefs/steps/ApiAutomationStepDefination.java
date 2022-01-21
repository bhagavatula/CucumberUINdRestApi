package com.apitest.automation.stepdefs.steps;

import com.apitest.automation.compare.FlexLogger;
import configs.ScenarioDataMap;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.retry.support.RetryTemplate;
import com.apitest.automation.pages.RestAssuredImpl;
import com.apitest.automation.stepdefs.steps.StepsInterface;
import com.apitest.automation.utils.ThreadLocalContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
public class ApiAutomationStepDefination implements StepsInterface {
    private static final String SCENARIO_TAG = "apiAutomationTesting";
    private static final Logger log = Logger.getLogger(ApiAutomationStepDefination.class.getName());
    private ScenarioDataMap scenarioMap;
    private static Scenario scenario;
    private RetryTemplate retryTemplate;
    RestAssuredImpl restAssuredImpl = new RestAssuredImpl();
    private String endpointUrl;
    Response response = null;
    public static final String JSON = ".json";
    protected static Map<String,String> expectedData = new HashMap<String,String>();
    public static  WebDriver driver = new ChromeDriver();

    @Before("@" + SCENARIO_TAG)
        public void init(Scenario scenario){
            FlexLogger.fetchScenario(scenario);
            ThreadLocalContext.setScenarioName(SCENARIO_TAG);
        }

    @Given("^url endpoint \"([^\"]*)\"$")
    public void getHealthEndPoint(String endpoint) {
        try {
            if (endpoint != null) {
//                endpointUrl;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


//    @Given ("^user given gmail \"([^\"]*)\"$")
//        public static void launchGmailApplication(String url){
//        try{
//
//            driver.get(url);
//            driver.manage().window().maximize();
//
//        }catch(Exception e){
//        }
//    }
//
//    @And("^user enter the user name \"([^\"]*)\"$")
//    public static void inpptusername(String username){
//        try{
//            driver.findElement(By.name("username")).sendKeys(username);
//        }catch(NoSuchElementException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("^user enter the password \"([^\"]*)\"$")
//    public static void inpptpasswod(String password) throws Exception{
//        try{
//            driver.findElement(By.name("password")).sendKeys(password);
//
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            WebDriverWait wait = new WebDriverWait(driver,2);
//            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("username")));
//            Thread.sleep(10);
//        }catch(NoSuchElementException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @When("click on signin button")
//    public static void clickonSignButton(){
//        try {
//            driver.findElement(By.name("sigin")).click();
//        }catch (NoSuchElementException e){
//            e.printStackTrace();
//        }
//    }
//
//    @Then("user validate the home page loaded")
//    public static void validateHomePage(){
//        try{
//
//        }catch (){
//
//        }
//    }
//
//
//
//    @Given("user given gmail \"([^\"]*)\"$")
//    public void getHealthEndPoint(String endpoint) {
//        try {
//            if (endpoint != null) {
////                endpointUrl;
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }


}
