package ru.yandex.practicum.stellarburger.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderPage {

    @FindBy(how = How.LINK_TEXT,using = "Личный Кабинет")
    private SelenideElement userAccountLink;

    @FindBy(how = How.XPATH, using = ".//div[contains(@class,'logo')]")
    private SelenideElement stellarBurgersLogo;

    @FindBy(how = How.LINK_TEXT, using = "Конструктор")
    private SelenideElement constructorLink;

    @Step("Click \"Личный Кабинет\" button")
    public void clickUserAccountLink() {
        userAccountLink.click();
    }

    @Step("Click constructor link")
    public void clickConstructorLink() {
        constructorLink.click();
    }

    @Step("Click StellarBurgers logo")
    public void clickStellarBurgersLogo() {
        stellarBurgersLogo.click();
    }
}
