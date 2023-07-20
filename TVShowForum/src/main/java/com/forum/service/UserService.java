package com.forum.service;

import com.forum.entity.Tvshow;
import com.forum.entity.User;
import com.forum.model.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();
    User findByEmail(String email);
    User findByUserName(String userName);
    void save(WebUser webUser);

    User findByIdWithReviews(int id);

    User findById(int id);
    void update(User user);
}
