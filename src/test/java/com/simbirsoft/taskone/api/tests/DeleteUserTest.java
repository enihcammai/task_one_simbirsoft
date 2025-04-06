package com.simbirsoft.taskone.api.tests;

import com.simbirsoft.taskone.api.utils.BaseRequest;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class DeleteUserTest {

    private RequestSpecification requestSpecification;

    @BeforeEach
    public void setup(){
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Test
    public void getAllUsers_shouldReturnUserList() throws Exception {

        given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .delete("/delete/1")
                .then()
                .statusCode(204);
    }
}
