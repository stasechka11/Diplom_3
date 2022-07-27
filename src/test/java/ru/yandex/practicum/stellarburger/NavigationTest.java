package ru.yandex.practicum.stellarburger;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.yandex.practicum.stellarburger.api.User;
import ru.yandex.practicum.stellarburger.api.UserClient;
import ru.yandex.practicum.stellarburger.api.UserResponse;
import ru.yandex.practicum.stellarburger.pages.LoginPage;
import ru.yandex.practicum.stellarburger.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;
import static ru.yandex.practicum.stellarburger.pages.MainPage.MAIN_PAGE_URL;

public class NavigationTest {
    User user;
    UserClient userClient;
    String accessToken;

    @After
    public void clear() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Check navigation to user account from main page")
    public void navigateToUserAccountTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickUserAccountLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.checkLoginFormDisplayed();
    }

    @Test
    @DisplayName("Check navigation to user account when user already logged in")
    public void navigateToAccountUserLoggedIn() {
        user = User.getRandomUser();
        userClient = new UserClient();
        UserResponse createUserResponse = userClient.createUser(user).as(UserResponse.class);
        accessToken = createUserResponse.getAccessToken();

        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());

        mainPage.clickUserAccountLink();
        webdriver().shouldHave(urlContaining("/account/profile"));
    }
}
