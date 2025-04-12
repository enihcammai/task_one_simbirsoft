package com.simbirsoft.taskone.api.tests;

import com.simbirsoft.taskone.api.pojo.EntityRequest;
import com.simbirsoft.taskone.api.pojo.EntityResponse;
import com.simbirsoft.taskone.api.utils.HttpStatus;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetEntityTest extends ApiBaseTest{

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
    public void testGetEntityById() throws Exception {

        EntityResponse response = given()
                .when()
                .get("/get/" + entityId)
                .then()
                .statusCode(HttpStatus.OK.getCode())
                .extract()
                .as(EntityResponse.class, ObjectMapperType.GSON);

        assertEquals(entityId, response.getId());
    }

    @Override
    void destroyData() {
        given()
                .when()
                .delete("/delete/" + entityId);
    }
}

