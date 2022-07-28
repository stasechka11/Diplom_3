package ru.yandex.practicum.stellarburger.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {
    public static final String FORGOT_PASSWORD_PAGE_PATH = "forgot-password";
    @FindBy(how = How.LINK_TEXT,using = "Войти")
    private SelenideElement loginLink;

    @Step("Click \"Войти\" link")
    public void clickLoginLink() {
        loginLink.click();
    }
}
