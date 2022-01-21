import com.apitest.automation.utils.ThreadLocalContext;
import org.testng.IReporter;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.lang.ref.Reference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Logger;

public class Retry implements IRetryAnalyzer {
    private static final Logger logger = Logger.getLogger(Retry.class.getName());
    int maxAttempts = 1;

    @Override
    public boolean retry(ITestResult result) {
        String resultScenarioName = Arrays.toString(result.getParameters()).split(",")[0].replaceAll("[\\[\\]\"]", "");
        int retryAttempt = RetryLocalContext.getRetryAttempt() != null ? Integer.valueOf(RetryLocalContext.getRetryAttempt()) : 0;
        if (ThreadLocalContext.getScenarioDetails().getName().equalsIgnoreCase(resultScenarioName)) {
            if (retryAttempt < maxAttempts) {
                threadLocalCleanUP();
                RetryLocalContext.setRetry(retryAttempt + 1);
                logger.info("::ReRunning Failed Scenario :" + resultScenarioName + " Attempt: " + RetryLocalContext.getRetryAttempt());
                return true;
            }
        }
        return false;
    }


    public static void threadLocalCleanUP() {
        try {
            Thread thread = Thread.currentThread();
            Field threadLocalField = Thread.class.getDeclaredField("threadLocals");
            threadLocalField.setAccessible(true);
            Object threadLocalTable = threadLocalField.get(thread);
            Class threadLocalMapClass = Class.forName("java.lang.ThreadLocal$ThreadLocalMap");
            Field tableField = threadLocalMapClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object table = tableField.get(threadLocalTable);
            Field referentField = Reference.class.getDeclaredField("referent");
            referentField.setAccessible(true);
            for (int i = 0; i < Array.getLength(table); i++) {
                Object entry = Array.get(table, i);
                if (entry != null) {
                    ThreadLocal threadLocal = (ThreadLocal) referentField.get(entry);
                    threadLocal.remove();
                }
            }
        } catch (Exception var10) {
            throw new IllegalStateException(var10);
        }
    }

}
