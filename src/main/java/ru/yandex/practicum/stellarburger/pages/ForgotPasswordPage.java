package ru.yandex.practicum.stellarburger.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotPasswordPage {
    @FindBy(how = How.LINK_TEXT,using = "Войти")
    private SelenideElement loginLink;

    @Step("Click \"Войти\" link")
    public void clickLoginLink() {
        loginLink.click();
    }
}
