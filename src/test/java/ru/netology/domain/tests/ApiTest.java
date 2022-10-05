package ru.netology.domain.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ru.netology.domain.data.DataHelper;
import ru.netology.domain.data.DBHelper;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {
    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    void shouldReturnSuccessPaymentMessage() {
        given()
                .spec(requestSpec)
                .body(new DataHelper.CardInfo("4444 4444 4444 4441", "09", "23", "Vasily Utkin", "123"))
                .when()
                .post("/payment")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturnSuccessCreditMessage() {
        given()
                .spec(requestSpec)
                .body(new DataHelper.CardInfo("4444 4444 4444 4441", "09", "23", "Vasily Utkin", "123"))
                .when()
                .post("/credit")
                .then()
                .statusCode(200);
    }
}


