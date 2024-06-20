package com.Models;

import org.openqa.selenium.WebDriver;

public class BrowserModel {

    private final WebDriver _webDriver;

    public BrowserModel(WebDriver driver){
        _webDriver = driver;
    }

    public WebDriver getWebDriver() {
        return _webDriver;
    }
}
