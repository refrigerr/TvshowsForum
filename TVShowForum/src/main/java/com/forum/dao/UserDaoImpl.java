package com.forum.dao;

import com.forum.entity.Tvshow;
import com.forum.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> query = entityManager.createQuery("from User where userName=:theUserName", User.class);
        query.setParameter("theUserName", userName);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception ignored) {

        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("from User where email=:theEmail", User.class);
        query.setParameter("theEmail", email);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception ignored) {

        }

        return user;
    }

    @Override
    public User findByUserNameWithReviews(String userName) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u " +
                "left join fetch u.reviews " +
                "where u.userName=:theUserName", User.class);
        query.setParameter("theUserName", userName);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception ignored) {

        }
        return user;
    }

    @Override
    public User findByIdWithReviews(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u " +
                        "left join fetch u.reviews " +
                        "where u.id=:theId", User.class);
        query.setParameter("theId", id);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception ignored) {

        }
        return user;
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u", User.class);
        List<User> users = null;
        try {
            users = query.getResultList();
        } catch (Exception ignored) {

        }
        return users;
    }


    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }


    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }
}
