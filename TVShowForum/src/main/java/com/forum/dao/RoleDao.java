package com.forum.dao;

import com.forum.entity.Role;

public interface RoleDao {

    Role findByName(String roleName);
}
