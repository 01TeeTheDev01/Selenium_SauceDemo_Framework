package com.SitePages;

import com.Helpers.UtilHelper;
import com.Repository.IBrowserRepository;
import com.SitePagesRepository.IBaseOperationsRepository;
import com.SitePagesRepository.IProductsPageRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage implements IProductsPageRepository, IBaseOperationsRepository {

    private final IBrowserRepository _browserRepository;

    @FindBy(xpath = "//span[@data-test='title']")
    WebElement pageTitleElement;

    @FindBy(xpath = "//a[@data-test='shopping-cart-link']")
    WebElement cartElement;

    @FindBy(xpath = "//div[@data-test='inventory-list']")
    WebElement inventoryList;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    WebElement menuButton;

    public ProductsPage(IBrowserRepository browserRepository){
        _browserRepository = browserRepository;
        PageFactory.initElements(_browserRepository.getWebDriver(), this);
    }

    @Override
    public void waitForElementsToLoad() {
        try{
            _browserRepository.browserWaitForElements(new WebElement[]{pageTitleElement, cartElement, inventoryList, menuButton});
        }catch (Exception error){
            System.out.printf("%s Wait Error: %S", ProductsPage.class.getName(), error.getMessage());
        }
    }

    @Override
    public boolean isPageTitleElementVisible() {
        return pageTitleElement.isDisplayed();
    }

    @Override
    public int getCartItemCount() {
        var itemCount = cartElement.getText();

        if(itemCount.isBlank() || itemCount.isEmpty())
            return 0;

        return Integer.parseInt(itemCount);
    }

    @Override
    public void addItemToCart(int index) {
        if(inventoryList == null)
            return;

        var items = inventoryList.findElements(By.className("inventory_item"));

        WebElement productButton;

        if(index < 0){
            productButton = items.get(0)
                    .findElement(By.className("inventory_item_description"))
                    .findElement(By.className("pricebar"))
                    .findElement(By.tagName("button"));

            addProduct(productButton);
        }
        else if(index > items.size()){
            productButton = items.get(items.size() - 1)
                    .findElement(By.className("inventory_item_description"))
                    .findElement(By.className("pricebar"))
                    .findElement(By.tagName("button"));

            addProduct(productButton);
        }
        else{
            for(int itemIndex = 0; itemIndex < index; itemIndex++){
                try{
                    productButton = items.get(itemIndex)
                            .findElement(By.className("inventory_item_description"))
                            .findElement(By.className("pricebar"))
                            .findElement(By.tagName("button"));

                    addProduct(productButton);

                    Thread.sleep(UtilHelper.HALT_INPUT_DELAY_IN_MILLISECONDS);
                }catch (Exception error){
                    System.out.printf("Add to cart error: %s", error.getMessage());
                }
            }
        }
    }

    @Override
    public void clickCartButton() {
        try{
            if(cartElement == null || !cartElement.isDisplayed()){
                System.out.println("Required element not found! Unable to submit shipping form!");
                return;
            }
            cartElement.click();
        }catch (Exception error){
            System.out.printf("%s click button error: %s", ProductsPage.class.getName(), error.getMessage());
        }
    }

    @Override
    public void clickMenuButton() {
        try{
            if(menuButton == null || !menuButton.isDisplayed()){
                System.out.println("Required element not found! Unable to submit shipping form!");
                return;
            }
            menuButton.click();
        }catch (Exception error){
            System.out.printf("%s click button error: %s", ProductsPage.class.getName(), error.getMessage());
        }
    }

    @Override
    public void clickLogoutButton() {
        try{
            if(menuButton == null || !menuButton.isDisplayed()){
                System.out.println("Required element not found! Unable to submit shipping form!");
                return;
            }
            menuButton.findElement(By.xpath("//a[@data-test='logout-sidebar-link']")).click();
        }catch (Exception error){
            System.out.printf("%s click button error: %s", ProductsPage.class.getName(), error.getMessage());
        }
    }

    private void addProduct(WebElement productButton) {
        if (productButton == null) {
            System.out.println("Failed to add product! Unable to locate requested element!");
            return;
        }

        productButton.click();
    }
}