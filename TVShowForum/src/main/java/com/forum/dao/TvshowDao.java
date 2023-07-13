package com.forum.dao;

import com.forum.entity.Category;
import com.forum.entity.Tvshow;
import com.forum.entity.User;

import java.util.List;
import java.util.Set;

public interface TvshowDao {

    List<Tvshow> findAll();
    Tvshow findById(int id);
    Tvshow findByTitle(String title);
    Tvshow findByTitleWithCategories(String title);
    Tvshow findByTitleWithReviews(String title);
    Tvshow findByIdWithReviews(int id);
    Tvshow findByIdWithEverything(int id);
    List<Tvshow> findByAgeRating(int ageRating);
    List<Tvshow> findAllByCategories(Set<Category> categories);
    void save(Tvshow tvshow);
    void update(Tvshow tvshow);
    void delete(Tvshow tvshow);
}
