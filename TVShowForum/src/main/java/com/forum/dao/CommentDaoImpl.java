package com.forum.dao;

import com.forum.entity.Comment;
import jakarta.persistence.EntityManager;
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
