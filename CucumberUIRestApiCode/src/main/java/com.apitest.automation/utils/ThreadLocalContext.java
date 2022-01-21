package com.apitest.automation.utils;

import configs.ScenarioDataMap;
import cucumber.api.Scenario;
import groovy.xml.Namespace;
import jdk.vm.ci.meta.Local;
import org.openqa.selenium.WebDriver;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadLocalContext {
    private static final ThreadLocal<ScenarioDataMap> ThreadLocalData = new ThreadLocal();
    public static Properties localconf;
    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    public static ThreadLocal<String> sauceSessionId  = new ThreadLocal();
    public static  ThreadLocal<Scenario> Scenario = new ThreadLocal();
    private static final ThreadLocal<String> ScenarioName = new ThreadLocal();
    private static final ThreadLocal<Long> globalTimeout = new ThreadLocal();
    private static final ThreadLocal<Long> globalPolling = new ThreadLocal();
    private static final ThreadLocal<Integer>  RETRY_ATTEMPT = new ThreadLocal();
    private static final ThreadLocal<ConcurrentHashMap<String,Object>> scenarioMap = new ThreadLocal<ConcurrentHashMap<String,Object>>();
    private static final ThreadLocal<HashMap<String,String>> fieldValues = new ThreadLocal<>();
    private static final ThreadLocal<List<String>> returnValues = new ThreadLocal<>();
    private static List<String> intitalgroupId = new ArrayList<>();
    private static List<String> completedgroupId = new ArrayList<>();
    private static Scenario scenario;





    public ThreadLocalContext(){
    }

    public static void addScenarioVariable(String key, Object value){
    }

    public static boolean isTakeScreenShots(){
        return "true".equals((LocalUtil.getProperty("takeScreenShots")));
    }
    public static boolean isLogLevelDebug(){
        return "debug".equals(LocalUtil.getProperty("logLevel"));
    }

    private static ConcurrentHashMap<String, Object> getScenarioMap(){
        if(scenarioMap.get()== null){
            scenarioMap.set(new ConcurrentHashMap<String,Object>());
            ThreadLocalContext.fieldValues.set(new HashMap<>());
        }
        return scenarioMap.get();
    }

    public static Object getScenarioVariable(String key){
        return getScenarioMap().get(key);
    }

    public static Object resetScenarioVariable(String key){
        return getScenarioMap().remove(key);
    }

    public  static String getScenarioId(){
        String scenarioName = "";
        if(scenario.getName() != null){
            scenarioName  = ThreadLocalContext.getScenarioName();
            scenarioName = scenario.getId();
            if(scenarioName == null){
                scenarioName = "";
            }
        }
        return scenarioName;
    }

    public static void initialize(){
        fieldValues.set(new HashMap<>());
        returnValues.set(new ArrayList<String>());
    }

    public static void addCurrentService(Object baseService){
        addScenarioVariable("service",baseService);
    }
    public static Object getCurrentService(){
        return getScenarioVariable("service");
    }
    public static void setFeatureFile(String uri){
        addScenarioVariable("featureFile",uri);
    }
    public static String getFeatureFile(){
        return (String) getScenarioVariable("featureFile");
    }

    public static String getFeatureFile(String uri){
        return (String) getScenarioVariable("featureFile");
    }

    public static void setLogScenarioName(String name){
        addScenarioVariable("logScenarioName",name);
    }
    public  static String getLogScenarioName(){
        return (String) getScenarioVariable("logScenarioName");
    }

    public static void resetScenarioCount(){
        addScenarioVariable("scenarioCount",1);
    }
    public static String getScenarioCount(){
        Integer count = (Integer) getScenarioVariable("scenarioCount");
        return String.valueOf(getScenarioVariable("scenarioCount"));
    }
    public static void incrementScenarioCount(){
        int count = (Integer) getScenarioVariable("scenarioCount");
        count++;
        addScenarioVariable("scenarioCount",count);
    }
    public static void incrementMessageSent(){
        int count = getMessageCount();
        count++;
        addScenarioVariable("messageCount",count);
    }
    public static Integer getMessageCount(){
        if(getScenarioVariable("messageCount") == null){
            addScenarioVariable("messageCount",0);
        }
        return (Integer) getScenarioVariable("messageCount");
    }
    public static Map<String, String> getFieldValues(){
        return fieldValues.get();
    }

    public static void storeFieldValue(String key, String value){
        getFieldValues().put(key,value);
    }

    public static String obtainFieldvalue(String key){
        return getFieldValues().get(key);
    }

    public static List<String> getReturnValues(){
        return returnValues.get();
    }
    public static void  storeReturnValue(String value){
        getReturnValues().add(value);
    }

    public static void setInitialGroupId(String groupId){
        intitalgroupId.add(groupId);
    }
    public static List<String> getIntitalgroupId(){
        return intitalgroupId;
    }

    public static void setCompletedgroupId(String groupId){
        completedgroupId.add(groupId);
    }

    public static List<String> getCompletedgroupId(){
        return completedgroupId;
    }


    public static void  setGlobalTimeout(Long timeout){
        globalTimeout.set(timeout);
    }
    public static long getGlobalTimeout(){
        Long returnValue = null;
        if (globalTimeout.get() != null){
            returnValue = (Long) globalTimeout.get();
        }
        return returnValue;
    }

    public static void setRetry(Integer retryAttempt) {
        RETRY_ATTEMPT.set(retryAttempt);
    }

    public static void setScenario(Scenario scenario) {
        Scenario.set(scenario);
    }

    public static void setScenarioData(ScenarioDataMap scenarioData) {
        ThreadLocalData.set(scenarioData);
    }

    public static void setScenarioName(String scenarioName) {
        ScenarioName.set(scenarioName);
    }

    public static Scenario getScenarioDetails() {
        Scenario returnValue = null;
        if (Scenario.get() != null) {
            returnValue = (Scenario) Scenario.get();
        }
        return returnValue;
    }

    public static ScenarioDataMap getMapData() {
        ScenarioDataMap returnValue = null;
        if (ThreadLocalData.get()!= null){
            returnValue = (ScenarioDataMap) ThreadLocalData.get();
        }
        return returnValue;
    }

    public static String getScenarioName(){
        String returnValue = null;
        if (ScenarioName.get()!=null){
            returnValue= (String)ScenarioName.get();
        }
        return returnValue;
    }

    public static Integer getRetryAttempt(){
        Integer returnValue  =null;
        if(RETRY_ATTEMPT.get() != null){
            returnValue = (Integer) RETRY_ATTEMPT.get();
        }
        return returnValue;
    }





}
