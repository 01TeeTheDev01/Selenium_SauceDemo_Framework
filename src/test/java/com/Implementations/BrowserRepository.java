package com.Implementations;

import com.Enums.BrowserType;
import com.Helpers.UtilHelper;
import com.Models.BrowserModel;
import com.Repository.IBrowserRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class BrowserRepository implements IBrowserRepository {

    private BrowserModel _driverModel;

    private final ArrayList<WebDriverManager> _webDriverManagers;

    public BrowserRepository(){
        _webDriverManagers = new ArrayList<>();
    }

    @Override
    public void addCompatibleBrowsers() {
        //_webDriverManagers.add(WebDriverManager.edgedriver());
        _webDriverManagers.add(WebDriverManager.chromedriver());
        _webDriverManagers.add(WebDriverManager.firefoxdriver());
        //_webDriverManagers.add(WebDriverManager.safaridriver());
    }

    @Override
    public void initBrowserManagers() {
        for(WebDriverManager manager : _webDriverManagers){
            manager.setup();
        }
    }

    @Override
    public void setBrowserWebDriver(BrowserType browserType, String webUrl) {

        switch (browserType){
            case Firefox:
                _driverModel = new BrowserModel(new FirefoxDriver(new FirefoxOptions().addArguments("--headless")));
                break;

            case Chrome:
                _driverModel = new BrowserModel(new ChromeDriver(new ChromeOptions().addArguments("--headless")));
                break;

            case Edge:
                _driverModel = new BrowserModel(new EdgeDriver(new EdgeOptions().addArguments("--headless")));
                break;

            case Safari:
                _driverModel = new BrowserModel(new SafariDriver());
                break;

            default:
                break;
        }

        _driverModel.getWebDriver().manage().window().maximize();

        _driverModel.getWebDriver().get(webUrl);
    }

    @Override
    public WebDriver getWebDriver() {
        if(_driverModel.getWebDriver() == null)
            return null;

        return _driverModel.getWebDriver();
    }

    @Override
    public void shutdownBrowser() {
        _driverModel.getWebDriver().quit();
    }

    @Override
    public void browserWaitForElements(WebElement[] webElements) {
        new WebDriverWait(_driverModel.getWebDriver(), Duration.ofSeconds(UtilHelper.BROWSER_DELAY_IN_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(webElements));
    }
}