package com.simbirsoft.taskone.api.tests;

import com.google.gson.Gson;
import com.simbirsoft.taskone.ui.service.PropertyService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class ApiBaseTest {

    protected static final Gson GSON = new Gson();
    protected static final RequestSpecification REQUEST_SPECIFICATION = buildRequestSpecification();
    protected volatile Integer entityId;

    @BeforeEach
    public void setup() {
        RestAssured.requestSpecification = REQUEST_SPECIFICATION;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        initData();
    }

    abstract void initData();
    abstract void destroyData();

    private static RequestSpecification buildRequestSpecification() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri(PropertyService.getInstance().getProperty("BASE_URL"))
                .setBasePath(PropertyService.getInstance().getProperty("BASE_PATH"))
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter());

        String port = PropertyService.getInstance().getProperty("PORT");
        if (port != null && !port.isBlank()) {
            builder.setPort(Integer.parseInt(port));
        }else {
            throw new RuntimeException("Port is unavailable");
        }


        return builder.build();
    }

    @AfterEach
    public void destroy(){
        destroyData();
    }
}
