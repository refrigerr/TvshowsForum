package com.forum.dao;

import com.forum.entity.Review;
import com.forum.entity.Tvshow;

import java.util.List;

public interface ReviewDao {
    List<Review> findAll();
    Review findById(int id);
    Review findBYIdWithComments(int id);
    void save(Review review);
    void update(Review review);
    void delete(Review review);
}
