package com.simbirsoft.taskone.api.tests;

import com.simbirsoft.taskone.api.pojo.EntityRequest;
import com.simbirsoft.taskone.api.pojo.EntityResponse;
import com.simbirsoft.taskone.api.utils.HttpStatus;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class UpdateEntityTest extends ApiBaseTest{

    @Override
    void initData() {
        EntityRequest request = EntityRequest.builder()
                .title("Заголовок ценности")
                .verified(true)
                .addition(EntityRequest.Addition.builder()
                        .additionalInfo("Не дополнительная формация")
                        .additionalNumber(200)
                        .build())
                .importantNumbers(List.of(30, 20, 10))
                .build();

        String responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .body(GSON.toJson(request))
                        .when()
                        .post("/create")
                        .then()
                        .extract()
                        .asString();

        entityId = GSON.fromJson(responseBody, Integer.class);
    }



    @Test
    public void testPatchEntityById() {
        EntityRequest requestUpdate = EntityRequest.builder()
                .title("Обновлённый заголовок")
                .verified(false)
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(requestUpdate)
                .when()
                .patch("/patch/" + entityId)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.getCode())
                .extract()
                .as(EntityResponse.class, ObjectMapperType.GSON);
    }

    @Override
    void destroyData() {
        given()
                .when()
                .delete("/delete/" + entityId);
    }
}
