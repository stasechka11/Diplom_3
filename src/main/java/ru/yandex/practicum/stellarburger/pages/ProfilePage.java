package ru.yandex.practicum.stellarburger.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {
    public static final String PROFILE_PAGE_PATH = "account/profile";

    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement logOutButton;

    @Step("Click logOut button")
    public void clickLogOutButton() {
        logOutButton.click();
    }
}
