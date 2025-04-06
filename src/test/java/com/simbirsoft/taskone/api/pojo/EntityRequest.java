package com.simbirsoft.taskone.api.pojo;


import com.google.gson.annotations.SerializedName;
import lombok.*;


import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class EntityRequest {

    @Builder.Default
    private Addition addition = Addition.builder().build();

    @SerializedName("important_numbers")
    @Builder.Default
    private List<Integer> importantNumbers = List.of(42, 87, 15);

    @Builder.Default
    private String title = "Заголовок сущности";

    @Builder.Default
    private boolean verified = true;

    public EntityRequest() {
    }

    @Getter
    @Setter
    @Builder
    public static class Addition {

        @SerializedName("additional_info")
        @Builder.Default
        private String additionalInfo = "Дополнительные сведения";


        @SerializedName("additional_number")
        @Builder.Default
        private int additionalNumber = 123;
    }
}
