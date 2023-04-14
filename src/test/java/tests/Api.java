package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import jdk.jfr.Label;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import settings.model.RegisterBody;
import settings.model.ResponseModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static settings.specs.LoginSpec.*;



public class Api {

    @Test
    @Tag("positive")
    @Feature("Позитивный сценарий")
    @DisplayName("Проверка запроса Detect - код 200")
    void testDetectUser() {
        Allure.step("Отправляем запрос detect", () -> {
            RestAssured.given(requestSpecification)
                    .when()
                    .get("api/v1/geco-ip/?detect_ip=true")
                    .then()
                    .log().all()
                    .spec(responseSpecification)
                    .statusCode(200);
        });
    }

    @Test
    @Tag("positive")
    @Feature("Позитивный сценарий")
    @DisplayName("Проверка запроса Detect - правильное определение города")
    void testDetectUserWithCity() {
        Allure.step("Отправляем запрос detect", () -> {
            RestAssured.given(requestSpecification)
                    .when()
                    .get("api/v1/geco-ip/?detect_ip=true")
                    .then()
                    .log().all()
                    .spec(responseSpecification)
                    .statusCode(200)
                    .body("city_name", is("Москва"));
        });
    }

    @Test
    @Tag("negative")
    @Story("Запрос статуса")
    @Feature("Негативный сценарии")
    @DisplayName("Запрос статуса без аутентификации")
    void statusSecureTest() {

        Allure.step("Отправляем запрос и проверяем ответ", () -> {
            RestAssured.given(requestSpecificationWeb)
                    .when()
                    .queryParam("userName", "Test")
                    .queryParam("password", "Test")
                    .post("getOrderStatusExtended.do")
                    .then()
                    .log().all()
                    .spec(responseSpecification)
                    .body("errorMessage", is("Доступ запрещён"));
        });
    }

    @Test
    @Tag("negative")
    @Feature("Негативный сценарии")
    @DisplayName("Проверка ответа сервиса при невалидном урле")
    void testDetectUserWithCity400() {
        Allure.step("Отправляем невалидный запрос detect и получаем код 400");
        ResponseModel responseModel = RestAssured.given(requestSpecification)
                .when()
                .get("api/v1/geco-ip/?detect_ip=true1")
                .then()
                .log().all()
                .spec(responseSpecification400)
                .body("status", is("VALIDATION_ERROR"))
                .extract().as(ResponseModel.class);

        Allure.step("Проверяем запрос", () ->
        {
            assertThat(responseModel.getStatus()).isEqualTo("VALIDATION_ERROR");
        });
    }

    @Test
    @Tag("negative")
    @Feature("Негативный сценарии")
    @DisplayName("Вход в личный кабинет c невалидными данными")
    void loginLK() {

        Allure.step("Отправляем запрос");
        RegisterBody registerBody = new RegisterBody();
        registerBody.setUsername("test");
        registerBody.setPassword("test");
        ResponseModel responseModel = RestAssured.given(requestSpecificationLogin)
                .when()
                .post()
                .then()
                .log().all()
                .spec(responseSpecification500)
                .extract().as(ResponseModel.class);

        Allure.step("Проверяем запрос", () -> {
            assertThat(responseModel.getError()).isEqualTo("Internal Server Error");
        });
    }
}

