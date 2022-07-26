package ru.yandex.practicum.stellarburger.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    @FindBy(how = How.XPATH, using = ".//form[contains(@class,'Auth_form')]")
    private SelenideElement loginForm;

    @FindBy(how = How.XPATH, using = ".//label[text()='Email']/following-sibling::input")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = ".//label[text()='Пароль']/following-sibling::input")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(how = How.LINK_TEXT,using = "Зарегистрироваться")
    private SelenideElement registerLink;

    @Step("Click register link")
    public void clickRegisterLink() {
        registerLink.click();
    }

    @Step("Click log in button")
    public void clickLogInButton() {
        loginButton.click();
    }

    @Step("Check login form is displayed")
    public void checkLoginFormDisplayed() {
        loginForm.shouldBe(Condition.visible);
    }

    @Step("Fill in login form")
    public void fillInLoginFrom(String email, String password) {
        emailField.setValue(email);
        passwordField.setValue(password);
        clickLogInButton();
    }
}
