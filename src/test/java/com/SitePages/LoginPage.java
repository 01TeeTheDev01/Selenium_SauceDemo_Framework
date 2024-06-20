package com.SitePages;

import com.Repository.IBrowserRepository;
import com.SitePagesRepository.IBaseOperationsRepository;
import com.SitePagesRepository.ILoginPageRepository;
import com.Repository.IUserRepository;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage implements ILoginPageRepository, IBaseOperationsRepository {

    private final IBrowserRepository _browserRepository;

    private final IUserRepository _userRepository;

    @FindBy(xpath = "//input[@id='user-name']")
    WebElement userNameElement;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordElement;

    @FindBy(xpath = "//input[@id='login-button']")
    WebElement loginButtonElement;

    @FindBy(xpath = "//h3[contains(@data-test,'error')]")
    WebElement errorTextElement;

    @FindBy(className = "error-button")
    WebElement errorTextCloseButton;

    public LoginPage(IBrowserRepository browserRepository, IUserRepository userRepository){
        _browserRepository = browserRepository;
        _userRepository = userRepository;
        PageFactory.initElements(_browserRepository.getWebDriver(), this);
    }

    @Override
    public void waitForElementsToLoad() {
        try{
            _browserRepository.browserWaitForElements(new WebElement[]{ userNameElement, passwordElement, loginButtonElement, errorTextElement });
        }catch (Exception error){
            System.out.printf("%s Wait Error: %S", LoginPage.class.getName(), error.getMessage());
        }
    }

    @Override
    public boolean isPageTitleElementVisible() {
        try{
            if(loginButtonElement == null || !loginButtonElement.isDisplayed()){
                System.out.println("Required element not found! Unable to submit shipping form!");
                return false;
            }
            return loginButtonElement.isDisplayed();
        }catch (Exception error){
            System.out.printf("%s click button error: %s", ProductsPage.class.getName(), error.getMessage());
            return false;
        }
    }

    @Override
    public void enterUserName(int rowIndex, int colIndex) {
        userNameElement.sendKeys(_userRepository.getUserName(rowIndex,colIndex));
    }

    @Override
    public void enterUserPassword(int rowIndex, int colIndex) {
        passwordElement.sendKeys(_userRepository.getUserPassword(rowIndex,colIndex));
    }

    @Override
    public void clearInputElements() {
        userNameElement.clear();
        passwordElement.clear();
    }

    @Override
    public boolean isErrorTextVisible() {
        return errorTextElement.isDisplayed();
    }

    @Override
    public void clickLoginButton() {
        try{
            if(loginButtonElement == null || !loginButtonElement.isDisplayed()){
                System.out.println("Required element not found! Unable to submit shipping form!");
                return;
            }
            loginButtonElement.click();
        }catch (Exception error){
            System.out.printf("%s click button error: %s", ProductsPage.class.getName(), error.getMessage());
        }
    }

    @Override
    public void clickErrorTextCloseButton() {
        try{
            if(errorTextCloseButton == null || !errorTextCloseButton.isDisplayed()){
                System.out.println("Required element not found! Unable to submit shipping form!");
                return;
            }
            errorTextCloseButton.click();
        }catch (Exception error){
            System.out.printf("%s click button error: %s", LoginPage.class.getName(), error.getMessage());
        }
    }
}