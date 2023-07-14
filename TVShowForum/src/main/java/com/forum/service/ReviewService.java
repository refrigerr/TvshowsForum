package com.forum.service;

import com.forum.entity.Review;
import com.forum.entity.Tvshow;
import com.forum.model.WebReview;
import com.forum.model.WebUser;

import java.util.List;

public interface ReviewService {

    List<Review> findAll();
    Review findById(int id);
    Review findByIdWithComments(int id);
    void save(WebReview webReview);
    void update(WebReview webReview);
    void delete(Review review);
}
