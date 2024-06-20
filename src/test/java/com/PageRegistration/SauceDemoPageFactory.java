package com.PageRegistration;

import com.SitePagesRepository.*;

public class SauceDemoPageFactory implements ISauceDemoPageFactory {
    private final ILoginPageRepository _loginPageRepository;
    private final IProductsPageRepository _productsPageRepository;
    private final ICartPageRepository _cartPageRepository;
    private final ICheckoutPageRepository _checkoutPageRepository;
    private final IConfirmationRepository _confirmationRepository;
    private final IThankYouPageRepository _thankYouPageRepository;

    public SauceDemoPageFactory(ILoginPageRepository loginPageRepository,
                                IProductsPageRepository productsPageRepository,
                                ICartPageRepository cartPageRepository,
                                ICheckoutPageRepository shippingPageRepository,
                                IConfirmationRepository confirmationRepository,
                                IThankYouPageRepository thankYouPageRepository){
        _loginPageRepository = loginPageRepository;
        _productsPageRepository = productsPageRepository;
        _cartPageRepository = cartPageRepository;
        _checkoutPageRepository = shippingPageRepository;
        _confirmationRepository = confirmationRepository;
        _thankYouPageRepository = thankYouPageRepository;
    }

    @Override
    public ILoginPageRepository getLoginPage() {
        return _loginPageRepository;
    }

    @Override
    public IProductsPageRepository getProductsPage() {
        return _productsPageRepository;
    }

    @Override
    public ICartPageRepository getCartPage() {
        return _cartPageRepository;
    }

    @Override
    public IConfirmationRepository getConfirmationPage() {
        return _confirmationRepository;
    }

    @Override
    public ICheckoutPageRepository getCheckoutPage() {
        return _checkoutPageRepository;
    }

    @Override
    public IThankYouPageRepository getThankYouPage() {
        return _thankYouPageRepository;
    }
}