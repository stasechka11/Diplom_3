package ru.yandex.practicum.stellarburger.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage  {
    public static final String  MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    public HeaderPage header = page(HeaderPage.class);
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

    @FindBy (how = How.XPATH, using = ".//div[contains(@class,'current')]/span")
    private SelenideElement currentSection;

    @FindBy (how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingSection;
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement logInAccountButton;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement makeOrderButton;

    @Step("Select buns section in constructor")
    public void clickBunsSection() {
        bunIngredientType.click();
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

    @Step("Check section {section} is current")
    public void checkSectionIsCurrent(String section) {
        currentSection.shouldHave(Condition.text(section));
    }

    @Step("Check \"Оформить заказ\" is visible")
    public void checkMakeOrderButtonIsVisible() {
        makeOrderButton.shouldBe(Condition.visible);
    }
}
