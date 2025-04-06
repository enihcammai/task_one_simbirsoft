package com.simbirsoft.taskone.api.tests;

import com.google.gson.Gson;
import com.simbirsoft.taskone.api.pojo.EntityResponse;
import com.simbirsoft.taskone.api.pojo.EntityWrapper;
import com.simbirsoft.taskone.api.utils.BaseRequest;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetAllUsersTest {

    private RequestSpecification requestSpecification;
    public EntityWrapper wrapper;
    private static final Gson gson = new Gson();

    @BeforeEach
    public void setup(){
        requestSpecification = BaseRequest.initRequestSpecification();
    }

    @Test
    public void getAllUsers_shouldReturnUserList() throws Exception {

        String responseBody = given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .get("/getAll")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        wrapper = gson.fromJson(responseBody, EntityWrapper.class);
        List<EntityResponse> entities = wrapper.getEntity();

        assertFalse(entities.isEmpty());
    }
}
