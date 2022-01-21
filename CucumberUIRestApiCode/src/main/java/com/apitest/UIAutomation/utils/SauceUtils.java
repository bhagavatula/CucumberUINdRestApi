package com.apitest.UIAutomation.utils;

import com.apitest.automation.utils.LocalUtil;
import com.apitest.automation.utils.ThreadLocalContext;
import com.saucelabs.saucerest.SauceREST;
import lombok.Value;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class SauceUtils {
    @Value("${env.environmentType}")
    private String environmentType;
    private static SauceREST sauceRest = null;

    public SauceUtils() {
    }

    private static SauceREST getSauceRest() {
        if (sauceRest == null) {
            String username = LocalUtil.getProperty("sauceUserName");
            String accessKey = LocalUtil.getProperty("sauceKey");
            sauceRest = new SauceREST(username, accessKey);
        }
        return sauceRest;
    }

    private static void updateResultOnSauceDashBoard(boolean isPass) {
        String sessionId = getSessionId();
        Map<String, Object> updates = getUpdatesHash(isPass);
        performUpdate(sessionId, updates);
    }

    public static void updateSauceDAshboard(HashMap<String, Object> updates) {
        String sessionId = getSessionId();
        performUpdate(sessionId, updates);
    }

    public static void performUpdate(String sessionId, Map<String, Object> updates) {
        SauceREST client = getSauceRest();
        if (sessionId != null && !sessionId.isEmpty()) {
            MiscUtils.setupHttpsProxy();
            client.updateJobInfo(sessionId, updates);
            MiscUtils.clearHttpsProxy();
        } else {
            CucumberLogUtils.logError("sauceSessionId is null or empty  ! Couldn't update Sauce dashboard");
        }
    }

    private static String getSessionId() {
        return (String) ThreadLocalContext.sauceSessionId.get();
    }

    private static Map<String, Object> getUpdatesHash(boolean testResult) {
        Map<String, Object> ret = new HashMap();
        ret.put("passed", testResult);
        return ret;
    }


}
