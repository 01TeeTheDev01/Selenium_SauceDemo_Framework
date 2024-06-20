package com.SitePagesRepository;

public interface IProductsPageRepository {
    int getCartItemCount();
    void addItemToCart(int index);
    void clickCartButton();
    void clickMenuButton();
    void clickLogoutButton();
}

