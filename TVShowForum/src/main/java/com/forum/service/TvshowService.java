package com.forum.service;

import com.forum.entity.Category;
import com.forum.entity.Tvshow;
import com.forum.model.WebTvshow;

import java.util.List;
import java.util.Set;

public interface TvshowService {
    List<Tvshow> findAll();

    Tvshow findById(int id);

    Tvshow findByName(String name);
    Tvshow findByIdWithReviews(int id);
    Tvshow findByIdWithEverything(int id);

    List<Tvshow> findByAgeRating(int ageRating);

    List<Tvshow> findByCategories(Set<Category> categories);

    void save(WebTvshow webTvshow);

    void update(WebTvshow webTvshow);
    void delete(Tvshow tvshow);
}
