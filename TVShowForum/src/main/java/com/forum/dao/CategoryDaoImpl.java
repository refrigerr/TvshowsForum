package com.forum.dao;

import com.forum.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao{


    EntityManager entityManager;

    @Autowired
    public CategoryDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Category findByName(String name) {
        TypedQuery<Category> query = entityManager.createQuery("select c from Category c where c.name = :theName", Category.class);
        query.setParameter("theName", name);
        return query.getSingleResult();
    }

    @Override
    public Category findById(int id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        TypedQuery<Category> query = entityManager.createQuery("select c from Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public void save(Category category) {
        entityManager.persist(category);
    }
}
