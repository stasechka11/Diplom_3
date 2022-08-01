package ru.yandex.practicum.stellarburger.tests;


import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.stellarburger.BaseTest;
import ru.yandex.practicum.stellarburger.api.User;
import ru.yandex.practicum.stellarburger.api.UserClient;
import ru.yandex.practicum.stellarburger.api.UserCredentials;
import ru.yandex.practicum.stellarburger.api.UserResponse;
import ru.yandex.practicum.stellarburger.pages.LoginPage;
import ru.yandex.practicum.stellarburger.pages.MainPage;
import ru.yandex.practicum.stellarburger.pages.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static ru.yandex.practicum.stellarburger.pages.MainPage.MAIN_PAGE_URL;
import static ru.yandex.practicum.stellarburger.pages.RegisterPage.REGISTER_PAGE_PATH;
@DisplayName("Register user tests")
public class RegisterUserTest extends BaseTest {
    User user;
    UserCredentials userCredentials;
    UserClient userClient;
    String accessToken;

    @Before
    public void setUp() {
        setUpBrowser();
    }

    @After
    public void clear() {
        if(driver != null) {
            driver.quit();
        }
        if(!(user ==null)) {
            userClient = new UserClient();
            userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
            UserResponse loginUserResponse = userClient.loginUser(userCredentials).as(UserResponse.class);
            accessToken = loginUserResponse.getAccessToken();
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Check user registration")
    public void registerUserTest() {
        user = User.getRandomUser();
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.header.clickUserAccountLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.fillInRegistrationForm(user.getName(), user.getEmail(), user.getPassword());

        loginPage.checkLoginFormDisplayed();
    }

    @Test
    @DisplayName("Check error message when password's length is less 6 characters")
    public void checkInCorrectPasswordMessage() {
        RegisterPage registerPage = open(MAIN_PAGE_URL + REGISTER_PAGE_PATH, RegisterPage.class);
        registerPage.checkPasswordErrorMessage();
    }
}
