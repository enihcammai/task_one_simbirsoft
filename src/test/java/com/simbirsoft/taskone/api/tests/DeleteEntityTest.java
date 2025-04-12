package com.simbirsoft.taskone.api.tests;

import com.simbirsoft.taskone.api.pojo.EntityRequest;
import com.simbirsoft.taskone.api.utils.HttpStatus;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class DeleteEntityTest extends ApiBaseTest{

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
    public void testDeleteEntityWithSerialization() throws Exception {
        given()
                .when()
                .delete("/delete/" + entityId)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.getCode());
    }

    @Override
    void destroyData() {
        entityId = null;
    }
}
