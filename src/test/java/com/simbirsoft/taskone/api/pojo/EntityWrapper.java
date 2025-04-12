package com.simbirsoft.taskone.api.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;


@Value
@Builder
public class EntityWrapper {
    @SerializedName("entity")
    public List<EntityResponse> entities;
}
