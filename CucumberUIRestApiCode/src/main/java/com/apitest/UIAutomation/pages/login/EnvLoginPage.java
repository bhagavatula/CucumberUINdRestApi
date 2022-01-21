package com.apitest.UIAutomation.pages.login;

import com.apitest.UIAutomation.objectRepository.AutoWireBeans;
import com.apitest.UIAutomation.vo.UserVo;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import lombok.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.open;
@SuppressWarnings("InfiniteRecursion")
@Profile("!local")
@Component
public class EnvLoginPage extends AutoWireBeans implements LoginPage{

    private String loginPageUrl;
    @Value("${env.environmentType}")
    private String environmentType;
    private Scenario scenario;
    private RetryTemplate retryTemplate;

    @Value("${download_retry.max_attempts}")
    int maxAttempts;

    @Before()
    public void initLogin(){
        initLogin();
    }

    @Before()
    public void intLogin(Scenario scenario){
        this.scenario = scenario;
    }

    @Before()

    @Override
    public void loginAs(UserVo userVo) {
        CustomSystemSetuup.initiateDriver();
        open(loginPageUrl);
        if(environmentType.contentEquals("prod")){
            try{
                Thread.sleep(10000);
                lqcUtil.PageRefresh();
                Thread.sleep(10000);
                lqcUtil.PageRefresh();
                Thread.sleep(10000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        retryTemplate (qca)
    }

    @Override
    public void logoutAndSwitchUser(UserVo uservo) {

    }
}
