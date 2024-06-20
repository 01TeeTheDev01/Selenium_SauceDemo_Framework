package com.Repository;

import com.Enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IBrowserRepository {
    void addCompatibleBrowsers();
    void initBrowserManagers();
    void setBrowserWebDriver(BrowserType browserType, String webUrl);
    WebDriver getWebDriver();
    void shutdownBrowser();
    void browserWaitForElements(WebElement[] webElements);
}

