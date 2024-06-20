package com.SitePages;

import com.Repository.IBrowserRepository;
import com.SitePagesRepository.IBaseOperationsRepository;
import com.SitePagesRepository.IConfirmationRepository;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage implements IConfirmationRepository, IBaseOperationsRepository {
    private final IBrowserRepository _browserRepository;

    @FindBy(xpath = "//span[@data-test='title']")
    WebElement pageTitleElement;

    @FindBy(xpath = "//div[@data-test='cart-list']")
    WebElement cartList;

    @FindBy(xpath = "//div[@data-test='subtotal-label']")
    WebElement subTotalElement;

    @FindBy(xpath = "//div[@data-test='tax-label']")
    WebElement taxElement;

    @FindBy(xpath = "//div[@data-test='total-label']")
    WebElement totalPriceElement;

    @FindBy(xpath = "//button[@data-test='finish']")
    WebElement finishButtonElement;

    public ConfirmationPage(IBrowserRepository browserRepository){
        _browserRepository = browserRepository;
        PageFactory.initElements(_browserRepository.getWebDriver(), this);
    }

    @Override
    public void waitForElementsToLoad() {
        try{
            _browserRepository.browserWaitForElements(new WebElement[]{ pageTitleElement, cartList,
                                                                        subTotalElement, taxElement,
                                                                        totalPriceElement, finishButtonElement });
        }catch (Exception error){
            System.out.printf("%s Wait Error: %S", ConfirmationPage.class.getName(), error.getMessage());
        }
    }

    @Override
    public boolean isPageTitleElementVisible() {
        return pageTitleElement.isDisplayed();
    }

    @Override
    public boolean calculateItemTotalPrice() {
        if(subTotalElement == null || taxElement == null || totalPriceElement == null){
            System.out.println("Required elements not found! Unable to proceed with calculation!");
            return false;
        }

        var subTotal = Double.parseDouble(subTotalElement.getText().split(" ")[2].substring(1));
        var tax = Double.parseDouble(taxElement.getText().split(" ")[1].substring(1));
        var total = Double.parseDouble(totalPriceElement.getText().split(" ")[1].substring(1));

        var finalTotal = subTotal + tax;

        var formattedFinalTotal = String.format("%.2f", finalTotal).replace(",",".");

        return total == Double.parseDouble(formattedFinalTotal);
    }

    @Override
    public boolean isCartItemsVisible() {
        return cartList.isDisplayed();
    }

    @Override
    public void clickFinishButton() {
        try{
            if(finishButtonElement == null || !finishButtonElement.isDisplayed()){
                System.out.println("Required element not found! Unable to submit shipping form!");
                return;
            }
            finishButtonElement.click();
        }catch (Exception error){
            System.out.printf("%s click button error: %s", ConfirmationPage.class.getName(), error.getMessage());
        }
    }
}