package com.forum.dao;

import com.forum.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    User findByEmail(String email);
    User findByUserNameWithReviews(String userName);
    User findByIdWithReviews(int id);
    void save(User user);
    void update(User user);
    void delete(User user);
}
