package com.apitest.UIAutomation.pages.wrappers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class WebDriverWrapper {
    public WebDriverWrapper() {

    }

    public void openPage(String url) {
        Selenide.open(url);
    }

    public void closPage() {
        Selenide.closeWebDriver();
    }

    public void refreshPage() {
        Selenide.refresh();
    }

    public byte[] logScreenshot() {
        byte[] scrBytes = (byte[]) ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        return scrBytes;
    }
}
