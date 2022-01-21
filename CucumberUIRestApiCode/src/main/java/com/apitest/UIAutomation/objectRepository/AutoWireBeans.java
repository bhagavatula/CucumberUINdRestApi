package com.apitest.UIAutomation.objectRepository;

import com.apitest.UIAutomation.pages.login.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoWireBeans {
    @Autowired
    public LoginPage loginPage;
}
