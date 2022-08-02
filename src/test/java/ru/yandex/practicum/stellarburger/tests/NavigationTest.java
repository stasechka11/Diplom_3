package ru.yandex.practicum.stellarburger.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.stellarburger.BaseTest;
import ru.yandex.practicum.stellarburger.pages.LoginPage;
import ru.yandex.practicum.stellarburger.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;
import static ru.yandex.practicum.stellarburger.pages.LoginPage.LOGIN_PAGE_PATH;
import static ru.yandex.practicum.stellarburger.pages.MainPage.MAIN_PAGE_URL;

@DisplayName("Navigation tests")
public class NavigationTest extends BaseTest {
    @Before
    public void setUp() {
        setUpBrowser();
    }

    @After
    public void clear() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Check navigation to user account from main page")
    public void navigateToUserAccountTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.header.clickUserAccountLink();
        LoginPage loginPage = page(LoginPage.class);
        webdriver().shouldHave(currentFrameUrl(MAIN_PAGE_URL + LOGIN_PAGE_PATH));
        loginPage.checkLoginFormDisplayed();
    }

    @Test
    @DisplayName("Check navigation to buns section in constructor")
    public void constructorBunsNavigationTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickSauceSection();
        mainPage.clickBunsSection();
        mainPage.checkSectionIsCurrent("Булки");
    }

    @Test
    @DisplayName("Check navigation to sauce section in constructor")
    public void constructorSauceNavigationTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickSauceSection();
        mainPage.checkSectionIsCurrent("Соусы");
    }

    @Test
    @DisplayName("Check navigation to filling section in constructor")
    public void constructorFillingNavigationTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickFillingSection();
        mainPage.checkSectionIsCurrent("Начинки");
    }
}
