package com.simbirsoft.taskone.api.utils;

import com.simbirsoft.taskone.ui.service.PropertyService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {

    public static RequestSpecification initRequestSpecification() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBaseUri(PropertyService.getInstance().getProperty("apiURL"))
                .setAccept(ContentType.JSON);

        return requestSpecBuilder.build();
    }
}
