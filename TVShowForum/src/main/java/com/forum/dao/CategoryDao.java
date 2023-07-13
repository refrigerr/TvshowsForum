package com.forum.dao;

import com.forum.entity.Category;

import java.util.List;

public interface CategoryDao {

    Category findByName(String name);

    Category findById(int id);
    List<Category> findAll();
    void save(Category category);

}
