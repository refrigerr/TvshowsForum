package com.forum.model;

import com.forum.entity.Category;
import jakarta.validation.constraints.NotNull;

public class WebCategory {

    @NotNull
    private String name;

    public WebCategory(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
