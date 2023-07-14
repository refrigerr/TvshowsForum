package com.forum.service;

import com.forum.dao.CategoryDao;
import com.forum.dao.TvshowDao;
import com.forum.entity.Category;
import com.forum.entity.Tvshow;
import com.forum.model.WebTvshow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TvshowServiceImpl implements TvshowService{

    TvshowDao tvshowDao;
    CategoryDao categoryDao;

    @Autowired
    public TvshowServiceImpl(TvshowDao tvshowDao, CategoryDao categoryDao){
        this.tvshowDao = tvshowDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Tvshow> findAll() {
        return tvshowDao.findAll();
    }

    @Override
    public Tvshow findById(int id) {
        return tvshowDao.findById(id);
    }

    @Override
    public Tvshow findByName(String name) {
        return tvshowDao.findByTitle(name);
    }


    @Override
    public Tvshow findByIdWithReviews(int id) {
        return tvshowDao.findByIdWithReviews(id);
    }

    @Override
    public Tvshow findByIdWithEverything(int id) {
        return tvshowDao.findByIdWithEverything(id);
    }

    @Override
    public List<Tvshow> findByAgeRating(int ageRating) {
        return tvshowDao.findByAgeRating(ageRating);
    }

    @Override
    public List<Tvshow> findByCategories(Set<Category> categories) {
        return tvshowDao.findAllByCategories(categories);
    }

    @Override
    public void save(WebTvshow webTvshow) {

        Tvshow tvshow = new Tvshow();
        tvshow.setTitle(webTvshow.getTitle());
        tvshow.setDescription(webTvshow.getDescription());
        tvshow.setCategories(webTvshow.getCategories());
        tvshow.setAgeRating(webTvshow.getAgeRating());
        tvshow.setReviews(new HashSet<>());

        tvshowDao.save(tvshow);
    }

    @Override
    public void update(WebTvshow webTvshow) {
        Tvshow tvshow = tvshowDao.findById(webTvshow.getId());
        tvshow.setTitle(webTvshow.getTitle());
        tvshow.setDescription(webTvshow.getDescription());
        tvshow.setCategories(webTvshow.getCategories());
        tvshow.setAgeRating(webTvshow.getAgeRating());
        tvshowDao.update(tvshow);
    }

    @Override
    public void delete(Tvshow tvshow) {
        tvshowDao.delete(tvshow);
    }
}
