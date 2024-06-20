package com.SitePages;

import com.Repository.IBrowserRepository;
import com.Repository.IFakeUserRepository;
import com.SitePagesRepository.IBaseOperationsRepository;
import com.SitePagesRepository.ICheckoutPageRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage implements ICheckoutPageRepository, IBaseOperationsRepository {

    private final IBrowserRepository _browserRepository;
    private final IFakeUserRepository _fakeUserRepository;

    @FindBy(xpath = "//span[@class='title']")
    WebElement pageTitleElement;

    @FindBy(xpath = "//div[@class='checkout_info']")
    WebElement checkoutFormElement;

    @FindBy(xpath = "//input[@data-test='continue']")
    WebElement continueButton;

    public CheckoutPage(IBrowserRepository browserRepository, IFakeUserRepository fakeUserRepository){
        _browserRepository = browserRepository;
        _fakeUserRepository = fakeUserRepository;
        PageFactory.initElements(_browserRepository.getWebDriver(), this);
    }

    @Override
    public void waitForElementsToLoad() {
        try{
            _browserRepository.browserWaitForElements(new WebElement[]{ pageTitleElement, checkoutFormElement, continueButton });
        }catch (Exception error){
            System.out.printf("%s Wait Error: %S", CheckoutPage.class.getName(), error.getMessage());
        }
    }

    @Override
    public boolean isPageTitleElementVisible() {
        return pageTitleElement.isDisplayed();
    }

    @Override
    public void completeCheckoutForm() {
        var firstNameElement = checkoutFormElement.findElement(By.xpath("//input[@data-test='firstName']"));
        var lastNameElement = checkoutFormElement.findElement(By.xpath("//input[@data-test='lastName']"));
        var zipCodeElement = checkoutFormElement.findElement(By.xpath("//input[@data-test='postalCode']"));

        if(firstNameElement == null || lastNameElement == null || zipCodeElement == null){
            System.out.println("Required elements not found! Operation aborted!");
            return;
        }

        _fakeUserRepository.generateFakeShippingInfo();
        firstNameElement.sendKeys(_fakeUserRepository.getFakeUserDetails().getFirstName());
        lastNameElement.sendKeys(_fakeUserRepository.getFakeUserDetails().getLastName());
        zipCodeElement.sendKeys(_fakeUserRepository.getFakeUserDetails().getZipCode());
    }

    @Override
    public void clickContinueButton() {
        try{
            if(continueButton == null || !continueButton.isDisplayed()){
                System.out.println("Required element not found! Unable to submit shipping form!");
                return;
            }
            continueButton.click();
        }catch (Exception error){
            System.out.printf("%s click button error: %s", CheckoutPage.class.getName(), error.getMessage());
        }
    }
}