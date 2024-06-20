package com.SitePages;

import com.Repository.IBrowserRepository;
import com.SitePagesRepository.IBaseOperationsRepository;
import com.SitePagesRepository.ICartPageRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage implements ICartPageRepository, IBaseOperationsRepository {

    private final IBrowserRepository _browserRepository;

    @FindBy(xpath = "//span[@data-test='title']")
    WebElement pageTitleElement;

    @FindBy(xpath = "//div[@data-test='cart-list']")
    WebElement cartList;

    @FindBy(xpath = "//button[@data-test='checkout']")
    WebElement checkoutButton;

    public CartPage(IBrowserRepository browserRepository){
        _browserRepository = browserRepository;
        PageFactory.initElements(_browserRepository.getWebDriver(), this);
    }

    @Override
    public void waitForElementsToLoad() {
        try{
            _browserRepository.browserWaitForElements(new WebElement[]{pageTitleElement, cartList, checkoutButton});
        }catch (Exception error){
            System.out.printf("%s Wait Error: %S", CartPage.class.getName(), error.getMessage());
        }
    }

    @Override
    public boolean isPageTitleElementVisible() {
        return pageTitleElement.isDisplayed();
    }

    @Override
    public int getCartItemCount() {
        var cartItems = cartList.findElements(By.className("cart_item"));
        if(cartItems == null || cartItems.isEmpty())
            return 0;
        return cartItems.size();
    }

    @Override
    public void clickCheckoutButton() {
        try{
            if(checkoutButton == null || !checkoutButton.isDisplayed()){
                System.out.println("Required element not found! Unable to submit shipping form!");
                return;
            }
            checkoutButton.click();
        }catch (Exception error){
            System.out.printf("%s click button error: %s", CartPage.class.getName(), error.getMessage());
        }
    }
}
