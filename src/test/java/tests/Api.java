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
import settings.model.RequestModel;
import settings.model.ResponseModel;
import settings.model.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static settings.specs.LoginSpec.*;

@Tags(value = {@Tag("web"), @Tag("api")})
@Epic("web")
@Label("Rest")
@DisplayName("Альфабанк тесты Rest api")
public class Api {

    @Test
    @Tags(value = {@Tag("regress"), @Tag("smoke")})
    @Story("Запрос статуса")
    @Feature("Негативный сценарии")
    @DisplayName("Запрос статуса без аутентификации")
    void statusSecureTest() {

        Allure.step("Отправляем запрос и проверяем ответ", () -> {
            RestAssured.given(requestSpecification)
                    .when()
                    .queryParam("userName", "Test")
                    .queryParam("password", "Test")
                    .post("getOrderStatusExtended.do")
                    .then()
                    .spec(responseSpecification)
                    .body("errorMessage", is("Доступ запрещён"));
        });
    }

    @Test
    @Tags(value = {@Tag("regress"), @Tag("smoke")})
    @Story("Операции с шаблонами")
    @Feature("Негативный сценарии")
    @DisplayName("Получение данных из шаблона без аутентификации")
    void getTemplatesSecureTest() {

        Allure.step("Отправляем запрос и проверяем ответ", () -> {
            RestAssured.given(requestSpecification)
                    .when()
                    .queryParam("userName", "Test")
                    .queryParam("password", "Test")
                    .queryParam("templateId", "Test")
                    .get("geco-ip/?detect_ip=true")
                    .then()
                    .spec(responseSpecification)
                    .body("errorCode", is(5));
        });
    }


    @Test
    @Tags(value = {@Tag("regress"), @Tag("smoke")})
    @Story("Запрос оплаты заказа")
    @Feature("Негативный сценарии")
    @DisplayName("Запрос оплаты заказа без аутентификации")
    void depositSecureTest() {

        Allure.step("Отправляем запрос");
        ResponseModel responseModel = RestAssured.given(requestSpecification)
                .when()
                .queryParam("userName", "Test")
                .queryParam("password", "Test")
                .queryParam("orderId", "Test")
                .queryParam("amount", "Test")
                .get("deposit.do")
                .then()
                .spec(responseSpecification)
                .extract().as(ResponseModel.class);

        Allure.step("Проверяем запрос", () -> {
            assertThat(responseModel.getErrorCode()).isEqualTo("5");
            assertThat(responseModel.getErrorMessage()).isEqualTo("Доступ запрещён");
        });
    }

    @Test
    @Tags(value = {@Tag("regress"), @Tag("smoke")})
    @Story("Операции с шаблонами")
    @Feature("Негативный сценарии")
    @DisplayName("Создание шаблона заказа без аутентификации")
    void createTemplatesSecureTest() {

        RequestModel requestModel = new RequestModel();

        Allure.step("Подготовка тела запроса", () -> {
            requestModel.setAmount(500);
            requestModel.setCurrency("доллар");
            requestModel.setDistributionChannel("развлечения");
            requestModel.setEndDate("2021-04-05T12:15:43");
            requestModel.setName("чек");
            requestModel.setPassword("123456");
            requestModel.setStartDate("2022-01-01T00:00:00");
            requestModel.setType(Type.SBP_QR);
            requestModel.setUsername("Тест");
        });

        Allure.step("Отправляем запрос");
        ResponseModel responseModel = RestAssured.given(requestSpecification)
                .when()
                .body(requestModel)
                .get("templates/createTemplate.do")
                .then()
                .spec(responseSpecification)
                .extract().as(ResponseModel.class);

        Allure.step("Проверяем запрос", () -> {
            assertThat(responseModel.getErrorCode()).isEqualTo("5");
            assertThat(responseModel.getErrorMessage()).isEqualTo("Доступ запрещён");
        });
    }

    @Test
    void testDetectUser() {

        Allure.step("Отправляем запрос detect", () -> {
            RestAssured.given(requestSpecification)
                    .when()
                    .get("api/v1/geco-ip/?detect_ip=true")
                    .then()
                    .spec(responseSpecification)
                    .statusCode(200);
        });
    }

    @Test
    void testDetectUserWithCity() {

        Allure.step("Отправляем запрос detect", () -> {
            RestAssured.given(requestSpecification)
                    .when()
                    .get("api/v1/geco-ip/?detect_ip=true")
                    .then()
                    .spec(responseSpecification)
                    .statusCode(200)
                    .body("city_name", is("Москва"));
        });
    }

    @Test
    void testDetectUserWithCity400() {

        Allure.step("Отправляем невалидный запрос detect и получаем код 400");
        ResponseModel responseModel = RestAssured.given(requestSpecification)
                .when()
                .get("api/v1/geco-ip/?detect_ip=true1")
                .then()
                .spec(responseSpecification400)
                .body("status", is("VALIDATION_ERROR"))
                .extract().as(ResponseModel.class);

        Allure.step("Проверяем запрос",()->
    {
        assertThat(responseModel.getStatus()).isEqualTo("VALIDATION_ERROR");
    });
}
}

