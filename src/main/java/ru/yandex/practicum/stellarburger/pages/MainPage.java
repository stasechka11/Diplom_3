package ru.yandex.practicum.stellarburger.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends HeaderPage {

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement logInAccountButton;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement makeOrderButton;

    @Step("Click \"Войти в аккаунт\"")
    public void clickLogInButton() {
        logInAccountButton.click();
    }

    @Step("Check \"Оформить заказ\" is visible")
    public void checkMakeOrderButtonIsVisible() {
        makeOrderButton.shouldBe(Condition.visible);
    }
}
