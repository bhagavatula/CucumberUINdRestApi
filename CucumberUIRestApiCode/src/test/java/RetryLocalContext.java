import cucumber.api.CucumberOptions;
import org.springframework.stereotype.Component;

@Component
public class RetryLocalContext {
    private static final ThreadLocal<Integer> RETRY_ATTEMPT = new ThreadLocal<Integer>();
    public static void setRetry(Integer retryAttempt){
        RETRY_ATTEMPT.set(retryAttempt);
    }
    public static Integer getRetryAttempt(){
        Integer returnValue = null;
        if(RETRY_ATTEMPT.get()!= null){
            returnValue = RETRY_ATTEMPT.get();
        }
        return returnValue;
    }
}
