package com.forum.service;

import com.forum.entity.Category;
import com.forum.model.WebCategory;
import com.forum.model.WebComment;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
    Category findById(int id);
    void save(WebCategory webCategory);
}
