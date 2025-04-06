package com.simbirsoft.taskone.api.tests;

import com.simbirsoft.taskone.api.pojo.EntityResponse;
import com.simbirsoft.taskone.api.utils.BaseRequest;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserTest {
    private RequestSpecification requestSpecification;

    @BeforeEach
    public void setup() {
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Test
    public void getAllUsers_shouldReturnUserList() throws Exception {

        EntityResponse response = given()
                .spec(requestSpecification)
                .when()
                .get("/get/20")
                .then()
                .statusCode(200)
                .extract()
                .as(EntityResponse.class, ObjectMapperType.GSON);

        assertEquals(20, response.getId());
    }
}

