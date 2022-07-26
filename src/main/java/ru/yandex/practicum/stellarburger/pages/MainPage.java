package ru.yandex.practicum.stellarburger.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
    public static final String  MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.LINK_TEXT,using = "Личный Кабинет")
    private SelenideElement userAccountLink;

    @Step("Click user account button")
    public void clickUserAccountButton() {
        userAccountLink.click();
    }


}
