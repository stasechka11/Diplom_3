package ru.yandex.practicum.stellarburger.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.stellarburger.BaseTest;
import ru.yandex.practicum.stellarburger.api.User;
import ru.yandex.practicum.stellarburger.api.UserClient;
import ru.yandex.practicum.stellarburger.api.UserResponse;
import ru.yandex.practicum.stellarburger.pages.*;

import static com.codeborne.selenide.Selenide.*;
import static ru.yandex.practicum.stellarburger.pages.ForgotPasswordPage.FORGOT_PASSWORD_PAGE_PATH;
import static ru.yandex.practicum.stellarburger.pages.LoginPage.LOGIN_PAGE_PATH;
import static ru.yandex.practicum.stellarburger.pages.MainPage.MAIN_PAGE_URL;
import static ru.yandex.practicum.stellarburger.pages.RegisterPage.REGISTER_PAGE_PATH;

@DisplayName("Login user tests")
public class LoginUserTest extends BaseTest {
    User user;
    UserClient userClient;
    String accessToken;

    @Before
    public void setUp() {
        setUpBrowser();
        user = User.getRandomUser();
        userClient = new UserClient();
        UserResponse createUserResponse = userClient.createUser(user).as(UserResponse.class);
        accessToken = createUserResponse.getAccessToken();
    }

    @After
    public void clear() {
        if(driver != null) {
            driver.quit();
        }
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Check user login by \"Войти в аккаунт\" button on main page")
    public void checkLogInByLoginButtonMainPageTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickLogInButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());
        mainPage.checkMakeOrderButtonIsVisible();
    }

    @Test
    @DisplayName("Check user login by \"Личный кабинет\" link on main page")
    public void checkLoginByUserAccountLinkMainPageTest() {
        MainPage mainPage = open(MAIN_PAGE_URL, MainPage.class);
        mainPage.clickUserAccountLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());
        mainPage.checkMakeOrderButtonIsVisible();
    }

    @Test
    @DisplayName("Check user login by \"Войти\" link on registration page")
    public void checkLoginRegisterPageTest() {
        RegisterPage registerPage = open(MAIN_PAGE_URL + REGISTER_PAGE_PATH, RegisterPage.class);
        registerPage.clickLoginLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());
        MainPage mainPage = page(MainPage.class);
        mainPage.checkMakeOrderButtonIsVisible();
    }

    @Test
    @DisplayName("Check user login on forgot password page")
    public void checkLoginForgotPasswordPageTest() {
        ForgotPasswordPage forgotPasswordPage = open(MAIN_PAGE_URL + FORGOT_PASSWORD_PAGE_PATH, ForgotPasswordPage.class);
        forgotPasswordPage.clickLoginLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());
        MainPage mainPage = page(MainPage.class);
        mainPage.checkMakeOrderButtonIsVisible();
    }

    @Test
    @DisplayName("Check user log out")
    public void userLogOutTest() {
        LoginPage loginPage = open(MAIN_PAGE_URL + LOGIN_PAGE_PATH, LoginPage.class);
        loginPage.fillInLoginFrom(user.getEmail(), user.getPassword());
        loginPage.clickUserAccountLink();

        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickLogOutButton();

        loginPage.checkLoginFormDisplayed();
    }
}
