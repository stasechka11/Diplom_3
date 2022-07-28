package ru.yandex.practicum.stellarburger.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends HeaderPage {

    @FindBy (how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunIngredientType;

    @FindBy (how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement sauceIngredientType;

    @FindBy (how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingIngredientType;

    @FindBy (how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bunSection;

    @FindBy (how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement sauceSection;

    @FindBy (how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingSection;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement logInAccountButton;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement makeOrderButton;

    @Step("Select buns section in constructor")
    public void clickBunsSection() {
        bunIngredientType.scrollIntoView(true).click();
    }
    @Step("Select sauce section in constructor")
    public void clickSauceSection() {
        sauceIngredientType.click();
    }
    @Step("Select filling section in constructor")
    public void clickFillingSection() {
        fillingIngredientType.click();
    }

    @Step("Click \"Войти в аккаунт\"")
    public void clickLogInButton() {
        logInAccountButton.click();
    }

    @Step("Check buns section is displayed")
    public void checkBunsSectionIsVisible() {
        bunSection.shouldBe(Condition.visible);
    }

    @Step("Check sauce section is displayed")
    public void checkSauceSectionIsVisible() {
        sauceSection.shouldBe(Condition.visible);
    }

    @Step("Check filling section is displayed")
    public void checkFillingSectionIsVisible() {
        fillingSection.shouldBe(Condition.visible);
    }

    @Step("Check \"Оформить заказ\" is visible")
    public void checkMakeOrderButtonIsVisible() {
        makeOrderButton.shouldBe(Condition.visible);
    }
}
