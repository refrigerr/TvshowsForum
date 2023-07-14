package com.forum.model;

import com.forum.entity.Tvshow;
import com.forum.entity.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WebReview {

    private int id = 0;

    private Tvshow tvshow;

    private User user;

    private String title;

    private String description;

    @Min(0)
    @Max(10)
    private int rating;

    public WebReview() {

    }

    public Tvshow getTvshow() {
        return tvshow;
    }

    public void setTvshow(Tvshow tvshow) {
        this.tvshow = tvshow;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
