package com.simbirsoft.taskone.api.tests;

import com.simbirsoft.taskone.api.pojo.EntityRequest;
import com.simbirsoft.taskone.api.utils.HttpStatus;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class CreateEntityTest extends ApiBaseTest {

    @Test
    public void testCreateEntityWithSerializationAndDeserialization() {
        EntityRequest request = EntityRequest.builder()
                .title("Заголовок ценности")
                .verified(true)
                .addition(EntityRequest.Addition.builder()
                        .additionalInfo("Не дополнительная формация")
                        .additionalNumber(200)
                        .build())
                .importantNumbers(List.of(30, 20, 10))
                .build();

        String responseBody = step("Создание пользователя через POST /create", () ->
                given()
                        .contentType(ContentType.JSON)
                        .body(GSON.toJson(request))
                        .when()
                        .post("/create")
                        .then()
                        .statusCode(HttpStatus.OK.getCode())
                        .extract()
                        .asString()
        );

        entityId = GSON.fromJson(responseBody, Integer.class);
    }

    @Override
    void initData() {
    }

    @Override
    void destroyData() {
        given()
                .when()
                .delete("/delete/" + entityId);
    }
}
