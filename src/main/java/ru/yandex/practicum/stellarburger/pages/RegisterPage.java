package ru.yandex.practicum.stellarburger.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {
    public static final String REGISTER_PAGE_PATH = "register";
    public static final String INCORRECT_PASSWORD_MESSAGE = "Некорректный пароль";

    @FindBy(how = How.XPATH, using = ".//label[text()='Имя']/following-sibling::input")
    private SelenideElement nameField;

    @FindBy(how = How.XPATH, using = ".//label[text()='Email']/following-sibling::input")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = ".//label[text()='Пароль']/following-sibling::input")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']/following::p")
    private SelenideElement passwordErrorMessage;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.LINK_TEXT,using = "Войти")
    private SelenideElement loginLink;

    @Step("Fill in the name field")
    public void setName(String name) {
        nameField.setValue(name);
    }

    @Step("Fill in the email field")
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    @Step("Fill in the password field")
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Click \"Войти\" link")
    public void clickLoginLink() {
        loginLink.click();
    }

    @Step("Fill in registration form")
    public void fillInRegistrationForm(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Check incorrect password message")
    public void checkPasswordErrorMessage() {
        Faker faker = new Faker();
        setPassword(faker.bothify("##??"));
        clickRegisterButton();
        passwordErrorMessage.shouldBe(Condition.visible);
        passwordErrorMessage.shouldHave(Condition.exactText(INCORRECT_PASSWORD_MESSAGE));
    }


}
