package com.simbirsoft.taskone.api.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EntityWrapper {
    @SerializedName("entity")
    private List<EntityResponse> entity;

    public List<EntityResponse> getEntity() {
        return entity;
    }

    public void setEntity(List<EntityResponse> entity) {
        this.entity = entity;
    }
}
