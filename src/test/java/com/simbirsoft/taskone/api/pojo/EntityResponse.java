package com.simbirsoft.taskone.api.pojo;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class EntityResponse {

    private int id;
    private Addition addition;

    private List<Integer> importantNumbers;

    private String title;
    private boolean verified;

    public EntityResponse() {
    }

    @Getter
    @Setter
    @Builder
    public static class Addition {
        private int id;

        private String additionalInfo;

        private int additionalNumber;
    }
}
