package com.UnitTests;

import com.Enums.BrowserType;
import com.Enums.ScreenShotFileType;
import com.Helpers.UtilHelper;
import com.Implementations.*;
import com.Models.UserModel;
import com.PageRegistration.ISauceDemoPageFactory;
import com.PageRegistration.SauceDemoPageFactory;
import com.Repository.*;
import com.SitePages.*;
import com.SitePagesRepository.*;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SauceDemoUITests {
    private final IBaseOperationsRepository _loginBase;
    private final IBaseOperationsRepository _productsBase;
    private final IBaseOperationsRepository _cartBase;
    private final IBaseOperationsRepository _checkoutBase;
    private final IBaseOperationsRepository _confirmationBase;
    private final IBaseOperationsRepository _thankYouBase;

    private final IBrowserRepository _browserRepository;
    private final ISauceDemoPageFactory _sauceDemoPageFactory;
    private final IScreenshotRepository _screenshotRepository;

    public SauceDemoUITests(){
        _browserRepository = new BrowserRepository();
        _browserRepository.setBrowserWebDriver(BrowserType.Firefox, UtilHelper.SAUCE_DEMO_URL);
        _screenshotRepository = new ScreenshotRepository(_browserRepository.getWebDriver());

        IWorkbookRepository workbookRepository = new WorkbookRepository(UtilHelper.WORKSHEET_DATAFILE_PATH, UtilHelper.WORKSHEET_TITLE);
        IUserRepository userRepository = new UserRepository(workbookRepository, new UserModel());
        ILoginPageRepository loginRepository = new LoginPage(_browserRepository, userRepository);
        IConfirmationRepository confirmationRepository = new ConfirmationPage(_browserRepository);
        IThankYouPageRepository thankYouPageRepository = new ThankYouPage(_browserRepository);
        IFakeUserRepository fakerRepository = new FakeUserRepository(new Faker());

        _loginBase = new LoginPage(_browserRepository, userRepository);
        _productsBase = new ProductsPage(_browserRepository);
        _cartBase = new CartPage(_browserRepository);
        _checkoutBase = new CheckoutPage(_browserRepository, fakerRepository);
        _confirmationBase = new ConfirmationPage(_browserRepository);
        _thankYouBase = new ConfirmationPage(_browserRepository);

        ICartPageRepository cartPageRepository = new CartPage(_browserRepository);
        IProductsPageRepository productsPageRepository = new ProductsPage(_browserRepository);
        ICheckoutPageRepository checkoutPageRepository = new CheckoutPage(_browserRepository, fakerRepository);

        _sauceDemoPageFactory = new SauceDemoPageFactory(loginRepository, productsPageRepository,
                                                         cartPageRepository, checkoutPageRepository,
                                                         confirmationRepository, thankYouPageRepository);
    }

    @BeforeTest
    public void initBrowserSetup(){
        _browserRepository.addCompatibleBrowsers();
        _browserRepository.initBrowserManagers();
    }

    @Test
    public void performLogin_Expected_Failure(){
        try{
            _sauceDemoPageFactory.getLoginPage().enterUserName(4, 3);
            
            _sauceDemoPageFactory.getLoginPage().enterUserPassword(2,2);
            
            _sauceDemoPageFactory.getLoginPage().clickLoginButton();
            
            _loginBase.waitForElementsToLoad();

            _screenshotRepository.takeScreenShot("LoginError", UtilHelper.CURRENT_DATE_TIME, ScreenShotFileType.PNG);

            Assert.assertTrue(_sauceDemoPageFactory.getLoginPage().isErrorTextVisible());
        }catch (Exception error){
            System.out.printf("Login Failed error: %s", error.getMessage());
        }
    }

    @Test(dependsOnMethods = {"performLogin_Expected_Failure"})
    public void performLogin_Expected_Success(){
        try{
            _sauceDemoPageFactory.getLoginPage().clearInputElements();
            
            _sauceDemoPageFactory.getLoginPage().clickErrorTextCloseButton();
            
            _sauceDemoPageFactory.getLoginPage().enterUserName(1, 0);
            
            _sauceDemoPageFactory.getLoginPage().enterUserPassword(1,1);
            
            _sauceDemoPageFactory.getLoginPage().clickLoginButton();
            
            _productsBase.waitForElementsToLoad();

            _screenshotRepository.takeScreenShot("LoginSuccess", UtilHelper.CURRENT_DATE_TIME, ScreenShotFileType.PNG);

            Assert.assertTrue(_productsBase.isPageTitleElementVisible());
        }catch (Exception error){
            System.out.printf("Login Success: %s", error.getMessage());
        }
    }

    @Test(dependsOnMethods = {"performLogin_Expected_Success"})
    public void addToCart_Expected_Success(){
        try{
            _screenshotRepository.takeScreenShot("before_adding_to_cart", UtilHelper.CURRENT_DATE_TIME, ScreenShotFileType.PNG);
            
            _sauceDemoPageFactory.getProductsPage().addItemToCart(UtilHelper.PRODUCT_INDEX);
            
            _screenshotRepository.takeScreenShot("after_adding_to_cart", UtilHelper.CURRENT_DATE_TIME, ScreenShotFileType.PNG);
            
            _sauceDemoPageFactory.getProductsPage().clickCartButton();
            
            _cartBase.waitForElementsToLoad();

            Assert.assertTrue(_cartBase.isPageTitleElementVisible());
        }catch (Exception error){
            System.out.printf("Cart Items error: %s", error.getMessage());
        }
    }

    @Test(dependsOnMethods = {"addToCart_Expected_Success"})
    public void verifyCartItems_Expected_Success(){
        try{
            _screenshotRepository.takeScreenShot("verify_cart_items", UtilHelper.CURRENT_DATE_TIME, ScreenShotFileType.PNG);
            
            _sauceDemoPageFactory.getCartPage().clickCheckoutButton();
            
            _checkoutBase.waitForElementsToLoad();

            Assert.assertTrue(_checkoutBase.isPageTitleElementVisible());
        }catch (Exception error){
            System.out.printf("Cart Items error: %s", error.getMessage());
        }
    }

    @Test(dependsOnMethods = {"verifyCartItems_Expected_Success"})
    public void completeCheckoutForm_Expected_Success(){
        try{
            _sauceDemoPageFactory.getCheckoutPage().completeCheckoutForm();
            
            _screenshotRepository.takeScreenShot("checkout_details", UtilHelper.CURRENT_DATE_TIME, ScreenShotFileType.PNG);
            
            _sauceDemoPageFactory.getCheckoutPage().clickContinueButton();
            
            _confirmationBase.waitForElementsToLoad();

            Assert.assertTrue(_confirmationBase.isPageTitleElementVisible());
        }catch (Exception error){
            System.out.printf("Checkout Form error: %s", error.getMessage());
        }
    }

    @Test(dependsOnMethods = {"completeCheckoutForm_Expected_Success"})
    public void verifyOrder_Expected_Success(){
        try{
            Assert.assertTrue(_sauceDemoPageFactory.getConfirmationPage().isCartItemsVisible());
            
            _screenshotRepository.takeScreenShot("verify_order_details", UtilHelper.CURRENT_DATE_TIME, ScreenShotFileType.PNG);
            
            Assert.assertTrue(_sauceDemoPageFactory.getConfirmationPage().calculateItemTotalPrice());

            _thankYouBase.waitForElementsToLoad();

            Assert.assertTrue(_thankYouBase.isPageTitleElementVisible());
        }catch (Exception error){
            System.out.printf("Verify Order error: %s", error.getMessage());
        }
    }

    @Test(dependsOnMethods = {"verifyOrder_Expected_Success"})
    public void confirmOrder_Expected_Success(){
        try{
            _screenshotRepository.takeScreenShot("confirm_order", UtilHelper.CURRENT_DATE_TIME, ScreenShotFileType.PNG);

            _thankYouBase.waitForElementsToLoad();

            Assert.assertTrue(_thankYouBase.isPageTitleElementVisible());
            
            _sauceDemoPageFactory.getConfirmationPage().clickFinishButton();
            
        }catch (Exception error){
            System.out.printf("Confirm Order error: %s", error.getMessage());
        }
    }

    @Test(dependsOnMethods = {"confirmOrder_Expected_Success"})
    public void goBackToHome_Expected_Success(){
        try {
            _screenshotRepository.takeScreenShot("return_to_home", UtilHelper.CURRENT_DATE_TIME, ScreenShotFileType.PNG);

            Assert.assertTrue(_productsBase.isPageTitleElementVisible());
            
            _sauceDemoPageFactory.getThankYouPage().clickBackToHomeButton();
        }catch (Exception error){
            System.out.printf("Product error: %s", error.getMessage());
        }
    }

    @Test(dependsOnMethods = {"confirmOrder_Expected_Success"})
    public void logout_Expected_Success(){
        try{
            _sauceDemoPageFactory.getProductsPage().clickMenuButton();
            
            _screenshotRepository.takeScreenShot("menu_flyout", UtilHelper.CURRENT_DATE_TIME, ScreenShotFileType.PNG);
            
            _sauceDemoPageFactory.getProductsPage().clickLogoutButton();

            Assert.assertTrue(_loginBase.isPageTitleElementVisible());
        }catch (Exception error){
            System.out.printf("Product error: %s", error.getMessage());
        }
    }

    @AfterTest
    public void shutdownBrowser(){
        _browserRepository.shutdownBrowser();
    }
}