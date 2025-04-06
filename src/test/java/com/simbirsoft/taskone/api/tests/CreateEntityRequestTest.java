package com.simbirsoft.taskone.api.tests;

import com.google.gson.Gson;
import com.simbirsoft.taskone.api.pojo.EntityRequest;
import com.simbirsoft.taskone.api.utils.BaseRequest;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CreateEntityRequestTest {

    private RequestSpecification requestSpecification;
    private static final Gson gson = new Gson();


    @BeforeEach
    public void setup() {
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Test
    public void testCreateUserWithSerialization() {
        EntityRequest request = EntityRequest.builder()
                .title("головок бущенности")
                .verified(true)
                .addition(EntityRequest.Addition.builder()
                        .additionalInfo("Не дополнительная формация")
                        .additionalNumber(200)
                        .build())
                .importantNumbers(List.of(30, 20, 10))
                .build();

        String responseBody = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        Integer entityId = gson.fromJson(responseBody, Integer.class);
        assertNotEquals(0,entityId);
    }

}
