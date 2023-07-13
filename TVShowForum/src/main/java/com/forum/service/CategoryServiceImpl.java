package com.forum.service;

import com.forum.dao.CategoryDao;
import com.forum.entity.Category;
import com.forum.model.WebCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public void save(WebCategory webCategory) {
        Category category = new Category();
        category.setName(webCategory.getName());

        categoryDao.save(category);
    }
}
