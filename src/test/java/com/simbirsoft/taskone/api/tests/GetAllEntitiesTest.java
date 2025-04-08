package com.simbirsoft.taskone.api.tests;

import com.simbirsoft.taskone.api.pojo.EntityResponse;
import com.simbirsoft.taskone.api.pojo.EntityWrapper;
import com.simbirsoft.taskone.api.utils.HttpStatus;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetAllEntitiesTest extends ApiBaseTest{

    public EntityWrapper wrapper;

    @Test
    public void testGetAllEntities() throws Exception {

        String responseBody = given()
                .when()
                .get("/getAll")
                .then()
                .statusCode(HttpStatus.OK.getCode())
                .extract()
                .body()
                .asString();

        wrapper = GSON.fromJson(responseBody, EntityWrapper.class);
        List<EntityResponse> entities = wrapper.getEntities();

        assertFalse(entities.isEmpty());
    }

    @Override
    void initData() {}

    @Override
    void destroyData() {
    }
}
