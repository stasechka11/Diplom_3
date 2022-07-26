package ru.yandex.practicum.stellarburger.api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class UserClient {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String BASE_PATH_USER = "/api/auth/";

    public static RequestSpecification getReqSpec() {
        return new RequestSpecBuilder().log(LogDetail.ALL)
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON).build();
    }

    @Step("Login user {userCredentials}")
    public Response loginUser(UserCredentials userCredentials) {
        return given()
                .spec(getReqSpec())
                .body(userCredentials)
                .when()
                .log().all()
                .post(BASE_URL + BASE_PATH_USER + "login");
    }

    @Step ("Delete user by accessToken: {accessToken}")
    public void deleteUser(String accessToken) {
        given()
                .spec(getReqSpec())
                .header("Authorization", accessToken)
                .when()
                .log().all()
                .delete(BASE_URL + BASE_PATH_USER + "user")
                .then()
                .assertThat()
                .statusCode(SC_ACCEPTED);
    }
}
