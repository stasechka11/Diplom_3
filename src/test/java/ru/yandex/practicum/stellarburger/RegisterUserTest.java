package ru.yandex.practicum.stellarburger;


import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

public class RegisterUserTest {
    User user;
    UserCredentials userCredentials;
    UserClient userClient;
    String accessToken;

    @Before
    public void setUp() {

    }

    @After
    public void clear() {
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
        mainPage.clickUserAccountLink();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.fillInRegistrationForm(user.getName(), user.getEmail(), user.getPassword());

        loginPage.checkLoginFormDisplayed();
    }

    @Test
    @DisplayName("Check error message when password's length is less 6 characters")
    public void checkInCorrectPasswordMessage() {
        RegisterPage registerPage = open(MAIN_PAGE_URL + "register", RegisterPage.class);
        registerPage.checkPasswordErrorMessage();
    }
}
