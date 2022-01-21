package com.apitest.automation.compare;

import cucumber.api.Scenario;
//import org.graalvm.compiler.lir.amd64.AMD64Binary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class FlexLogger {
    private static Scenario scenario;
    private final static Logger LOGGER = Logger.getLogger(String.valueOf(FlexLogger.class));

    public static void logFail(String msg){
        LOGGER.info("fail: "+msg);
    }
    public static void logInfo(String msg){
        LOGGER.info("Info: "+msg);
    }

    public static void fetchScenario(Scenario runtimescenaio){
        scenario = runtimescenaio;
    }

    public static void report(String msg){
        Date datVal = new Date();
        SimpleDateFormat dtFrmt = new SimpleDateFormat("MM/dd/yyyy");
        String reqDtFormt = dtFrmt.format(datVal);
        scenario.write(reqDtFormt+":" +msg);
    }

    public static void report(CompareSummary summary){
        if(summary!= null){
            report(summary.toString());
        }
    }
}
