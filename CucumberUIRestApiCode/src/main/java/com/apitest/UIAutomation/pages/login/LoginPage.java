package com.apitest.UIAutomation.pages.login;

import com.apitest.UIAutomation.vo.UserVo;

public interface LoginPage {
    void loginAs(UserVo userVo);
    void logoutAndSwitchUser(UserVo uservo);
}
