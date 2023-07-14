package com.forum.service;

import com.forum.dao.ReviewDao;
import com.forum.entity.Review;
import com.forum.model.WebReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{


    ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }

    @Override
    public List<Review> findAll() {
        return reviewDao.findAll();
    }

    @Override
    public Review findById(int id) {
        return reviewDao.findById(id);
    }

    @Override
    public Review findByIdWithComments(int id) {
        return reviewDao.findBYIdWithComments(id);
    }

    @Override
    public void save(WebReview webReview) {
        Review review = new Review();
        review.setTitle(webReview.getTitle());
        review.setDescription(webReview.getDescription());
        review.setUser(webReview.getUser());
        review.setTvshow(webReview.getTvshow());
        review.setRating(webReview.getRating());

        reviewDao.save(review);

    }

    @Override
    public void update(WebReview webReview) {
        Review review = reviewDao.findById(webReview.getId());
        review.setTitle(webReview.getTitle());
        review.setDescription(webReview.getDescription());
        review.setRating(webReview.getRating());
        reviewDao.update(review);
    }

    @Override
    public void delete(Review review) {
        reviewDao.delete(review);
    }
}
