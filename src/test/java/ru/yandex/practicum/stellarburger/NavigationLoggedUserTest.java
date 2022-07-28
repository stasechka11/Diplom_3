package ru.yandex.practicum.stellarburger;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.stellarburger.api.User;
import ru.yandex.practicum.stellarburger.api.UserClient;
import ru.yandex.practicum.stellarburger.api.UserResponse;
import ru.yandex.practicum.stellarburger.pages.LoginPage;
import ru.yandex.practicum.stellarburger.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;
import static ru.yandex.practicum.stellarburger.pages.MainPage.MAIN_PAGE_URL;

public class NavigationLoggedUserTest {
    User user;
    UserClient userClient;
    String accessToken;

    @Before
    public void setUp() {
        user = User.getRandomUser();
        userClient = new UserClient();
        UserResponse createUserResponse = userClient.createUser(user).as(UserResponse.class);
        accessToken = createUserResponse.getAccessToken();
    }

    @After
    public void clear() {
            userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Check navigation to user account when user already logged in")
    public void navigateToAccountUserLoggedInTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());

        mainPage.clickUserAccountLink();
        webdriver().shouldHave(currentFrameUrl(MAIN_PAGE_URL + "account/profile"));
    }

    @Test
    @DisplayName("Check navigation from user account to main page by clicking Constructor link")
    public void navigateMainPageConstructorLinkTest() {
        LoginPage loginPage = open(MAIN_PAGE_URL + "login", LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());

        loginPage.clickConstructorLink();
        webdriver().shouldHave(currentFrameUrl(MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Check navigation from user account to main page by clicking StellarBurgers logo")
    public void navigateMainPageStellarBurgersLogoTest() {
        LoginPage loginPage = open(MAIN_PAGE_URL + "login", LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());

        loginPage.clickStellarBurgersLogo();
        webdriver().shouldHave(currentFrameUrl(MAIN_PAGE_URL));
    }
}
