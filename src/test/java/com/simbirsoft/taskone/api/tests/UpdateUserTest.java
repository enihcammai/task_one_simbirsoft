package com.simbirsoft.taskone.api.tests;

import com.simbirsoft.taskone.api.pojo.EntityRequest;
import com.simbirsoft.taskone.api.pojo.EntityResponse;
import com.simbirsoft.taskone.api.utils.BaseRequest;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


public class UpdateUserTest {

    private RequestSpecification requestSpecification;

    @BeforeEach
    public void setup(){
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Test
    public void testPatchEntityById() {
        EntityRequest requestUpdate = EntityRequest.builder()
                .title("Обновлённый заголовок")
                .verified(false)
                .build();

        given()
                .spec(BaseRequest.initRequestSpecification())
                .contentType(ContentType.JSON)
                .body(requestUpdate)
                .when()
                .patch("/patch/1")
                .then()
                .statusCode(204)
                .extract()
                .as(EntityResponse.class, ObjectMapperType.GSON);
    }
}
