package com.forum.dao;

import com.forum.entity.Comment;
import com.forum.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl implements CommentDao{

    EntityManager entityManager;

    @Autowired
    public CommentDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Comment findById(int id) {
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c where c.id =: theId", Comment.class);
        query.setParameter("theId", id);
        Comment comment = null;
        try {
            comment = query.getSingleResult();
        }catch (Exception ignored){

        }
        return comment;
    }

    @Override
    @Transactional
    public void save(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    @Transactional
    public void update(Comment comment) {
        entityManager.merge(comment);
    }

    @Override
    @Transactional
    public void delete(Comment comment) {
        entityManager.remove(comment);
    }
}
