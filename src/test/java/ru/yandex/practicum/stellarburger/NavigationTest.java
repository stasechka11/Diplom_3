package ru.yandex.practicum.stellarburger;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.practicum.stellarburger.pages.LoginPage;
import ru.yandex.practicum.stellarburger.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;
import static ru.yandex.practicum.stellarburger.pages.HeaderPage.MAIN_PAGE_URL;

public class NavigationTest {
    @Test
    @DisplayName("Check navigation to user account from main page")
    public void navigateToUserAccountTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickUserAccountLink();
        LoginPage loginPage = page(LoginPage.class);
        webdriver().shouldHave(currentFrameUrl(MAIN_PAGE_URL + "login"));
        loginPage.checkLoginFormDisplayed();
    }

    @Test
    @DisplayName("Check navigation to buns section in constructor")
    public void constructorBunsNavigationTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickSauceSection();
        mainPage.clickBunsSection();
        mainPage.checkBunsSectionIsVisible();
    }

    @Test
    @DisplayName("Check navigation to sauce section in constructor")
    public void constructorSauceNavigationTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickSauceSection();
        mainPage.checkSauceSectionIsVisible();
    }

    @Test
    @DisplayName("Check navigation to filling section in constructor")
    public void constructorFillingNavigationTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickFillingSection();
        mainPage.checkFillingSectionIsVisible();
    }
}
