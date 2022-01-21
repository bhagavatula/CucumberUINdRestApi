package com.apitest.UIAutomation.pages;

import com.apitest.UIAutomation.pages.wrappers.DropdownWrapper;
import com.apitest.UIAutomation.pages.wrappers.PageBaseTemplate;
import com.apitest.UIAutomation.pages.wrappers.TextWrapper;
import com.apitest.UIAutomation.pages.wrappers.UploadFileWrapper;
import com.apitest.automation.utils.CommonUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Component
public class LoginPage extends PageBaseTemplate {
    @PostConstruct
    public void init(){
        getPageElements("Login_Page_Config.yaml");
    }
    private static  final Logger log = Logger.getLogger(LoginPage.class.getName());

    public TextWrapper loginUserName;
    public TextWrapper  loginPassword;
    public DropdownWrapper selectUser;
    public CommonUtils LogOut;
    public UploadFileWrapper chooseFileText;
}
