package com.forum.service;

import com.forum.entity.Tvshow;
import com.forum.entity.User;
import com.forum.model.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User findByUserName(String userName);
    void save(WebUser webUser);

}
