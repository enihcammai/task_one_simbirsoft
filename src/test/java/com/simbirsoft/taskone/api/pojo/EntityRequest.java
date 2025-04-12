package com.simbirsoft.taskone.api.pojo;


import com.google.gson.annotations.SerializedName;
import lombok.*;


import java.util.List;

@Value
@Builder
public class EntityRequest {

    @Builder.Default
    Addition addition = Addition.builder().build();

    @SerializedName("important_numbers")
    @Builder.Default
    List<Integer> importantNumbers = List.of(42, 87, 15);

    @Builder.Default
    String title = "Заголовок сущности";

    @Builder.Default
    boolean verified = true;

    @Value
    @Builder
    public static class Addition {

        @SerializedName("additional_info")
        @Builder.Default
        String additionalInfo = "Дополнительные сведения";

        @SerializedName("additional_number")
        @Builder.Default
        int additionalNumber = 123;
    }
}
