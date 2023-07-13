package com.forum.dao;

import com.forum.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao{

    EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Role findByName(String roleName) {
        TypedQuery<Role> query = entityManager.createQuery("from Role where name=:theRoleName", Role.class);
        query.setParameter("theRoleName", roleName);

        Role role = null;

        try {
            role = query.getSingleResult();
        }catch (Exception e){

        }

        return role;
    }
}
