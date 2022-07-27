package ru.yandex.practicum.stellarburger;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.practicum.stellarburger.pages.LoginPage;
import ru.yandex.practicum.stellarburger.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static ru.yandex.practicum.stellarburger.pages.MainPage.MAIN_PAGE_URL;

public class NavigationTest {

    @Test
    @DisplayName("Check navigation to user account from main page")
    public void navigateToUserAccountTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickUserAccountLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.checkLoginFormDisplayed();
    }
}
