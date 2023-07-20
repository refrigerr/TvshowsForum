package com.forum.dao;

import com.forum.entity.User;

import java.util.List;

public interface UserDao {

    User findByUserName(String userName);
    User findByEmail(String email);
    User findByUserNameWithReviews(String userName);
    User findByIdWithReviews(int id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(User user);

    User findById(int id);
}
