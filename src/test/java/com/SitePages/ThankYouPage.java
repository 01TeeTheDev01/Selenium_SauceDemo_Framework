package com.SitePages;

import com.Repository.IBrowserRepository;
import com.SitePagesRepository.IBaseOperationsRepository;
import com.SitePagesRepository.IThankYouPageRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankYouPage implements IThankYouPageRepository, IBaseOperationsRepository {
    private final IBrowserRepository _browserRepository;

    @FindBy(xpath = "//span[@data-test='title']")
    WebElement pageTitleElement;

    @FindBy(xpath = "//div[@data-test='checkout-complete-container']")
    WebElement completeOrderElement;

    public ThankYouPage(IBrowserRepository browserRepository){
        _browserRepository = browserRepository;
        PageFactory.initElements(_browserRepository.getWebDriver(), this);
    }

    @Override
    public void waitForElementsToLoad() {
        try{
            _browserRepository.browserWaitForElements(new WebElement[]{ pageTitleElement, completeOrderElement });
        }catch (Exception error){
            System.out.printf("%s Wait Error: %S", ThankYouPage.class.getName(), error.getMessage());
        }
    }

    @Override
    public boolean isPageTitleElementVisible() {
        return pageTitleElement.isDisplayed();
    }

    @Override
    public void clickBackToHomeButton() {
        try{
            if(completeOrderElement == null || !completeOrderElement.isDisplayed()){
                System.out.println("Required element not found! Unable to submit shipping form!");
                return;
            }

            completeOrderElement.findElement(By.id("back-to-products")).click();

        }catch (Exception error){
            System.out.printf("%s click button error: %s", ThankYouPage.class.getName(), error.getMessage());
        }
    }
}
