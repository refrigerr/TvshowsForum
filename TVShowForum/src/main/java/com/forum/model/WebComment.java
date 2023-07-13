package com.forum.model;

import com.forum.entity.Review;
import com.forum.entity.User;
import jakarta.validation.constraints.NotNull;

public class WebComment {

    private int id = 0;

    @NotNull
    private Review review;

    @NotNull
    private User user;

    @NotNull
    private String body;

    public WebComment() {

    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
