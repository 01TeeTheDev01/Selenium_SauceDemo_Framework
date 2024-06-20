package com.SitePagesRepository;

public interface ILoginPageRepository {
    void enterUserName(int rowIndex, int colIndex);
    void enterUserPassword(int rowIndex, int colIndex);
    void clearInputElements();
    boolean isErrorTextVisible();
    void clickLoginButton();
    void clickErrorTextCloseButton();
}

