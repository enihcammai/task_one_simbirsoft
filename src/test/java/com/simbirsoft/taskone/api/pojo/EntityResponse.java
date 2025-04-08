package com.simbirsoft.taskone.api.pojo;



import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;


@Value
@Builder
public class EntityResponse {
    int id;
    Addition addition;

    @SerializedName("important_numbers")
    List<Integer> importantNumbers;
    String title;
    boolean verified;

    @Value
    @Builder
    public static class Addition {
        int id;

        @SerializedName("additional_info")
        String additionalInfo;

        @SerializedName("additional_number")
        int additionalNumber;
    }
}
