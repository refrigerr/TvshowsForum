package com.forum.model;

import com.forum.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class WebTvshow {

    private int id = 0;

    @Size(min = 1, message = "minimum size is 1")
    @NotBlank(message = "can not be blank")
    private String title;

    private String description;

    private Set<Category> categories = new HashSet<>();

    private int ageRating;

    public WebTvshow() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addCategory(Category category){

        categories.add(category);
    }


}
