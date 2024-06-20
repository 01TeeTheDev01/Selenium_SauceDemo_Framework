package com.PageRegistration;

import com.SitePagesRepository.*;

public interface ISauceDemoPageFactory {
    ILoginPageRepository getLoginPage();
    IProductsPageRepository getProductsPage();
    ICartPageRepository getCartPage();
    IConfirmationRepository getConfirmationPage();
    ICheckoutPageRepository getCheckoutPage();
    IThankYouPageRepository getThankYouPage();
}