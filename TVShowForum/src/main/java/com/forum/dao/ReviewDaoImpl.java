package com.forum.dao;

import com.forum.entity.Review;
import com.forum.entity.Tvshow;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewDaoImpl implements ReviewDao{

    EntityManager entityManager;

    @Autowired
    public ReviewDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<Review> findAll() {
        TypedQuery<Review> query = entityManager.createQuery("select r from Review r", Review.class);
        List<Review> reviews = new ArrayList<>();
        try {
            reviews = query.getResultList();
        }catch (Exception ignored){

        }

        return reviews;
    }


    @Override
    public Review findById(int id) {
        TypedQuery<Review> query = entityManager.createQuery("select r from Review r where r.id =: theId", Review.class);
        query.setParameter("theId", id);
        Review review = null;
        try {
            review = query.getSingleResult();
        }catch (Exception ignored){

        }
        return review;
    }

    @Override
    public Review findBYIdWithComments(int id) {
        TypedQuery<Review> query = entityManager.createQuery("select r from Review r " +
                "left join fetch r.comments c " +
                "where r.id =: theId", Review.class);
        query.setParameter("theId", id);
        Review review = null;
        try {
            review = query.getSingleResult();
        }catch (Exception ignored){

        }
        return review;
    }

    @Override
    @Transactional
    public void save(Review review) {
        entityManager.persist(review);
    }

    @Override
    @Transactional
    public void update(Review review) {
        entityManager.merge(review);
    }

    @Override
    @Transactional
    public void delete(Review review) {
        entityManager.remove(review);
    }
}
