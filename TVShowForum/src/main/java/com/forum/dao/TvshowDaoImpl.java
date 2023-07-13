package com.forum.dao;

import com.forum.entity.Category;
import com.forum.entity.Tvshow;
import com.forum.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class TvshowDaoImpl implements TvshowDao{

    EntityManager entityManager;

    @Autowired
    public TvshowDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<Tvshow> findAll() {
        TypedQuery<Tvshow> query = entityManager.createQuery("select t from Tvshow t " +
                "left join fetch t.categories", Tvshow.class);
        return query.getResultList();
    }

    @Override
    public Tvshow findById(int id) {
        return entityManager.find(Tvshow.class, id);
    }

    @Override
    public Tvshow findByTitle(String title) {
        TypedQuery<Tvshow> query = entityManager.createQuery("select t from Tvshow t where t.title=:theTitle", Tvshow.class);
        query.setParameter("theTitle", title);
        Tvshow tvshow = null;
        try {
            tvshow = query.getSingleResult();
        }catch (Exception ignored){

        }
        return tvshow;
    }

    @Override
    public Tvshow findByTitleWithCategories(String title) {
        TypedQuery<Tvshow> query = entityManager.createQuery(
                "select t from Tvshow t " +
                        "join fetch t.categories " +
                        "where t.title = :theTitle ", Tvshow.class);
        query.setParameter("theTitle", title);
        Tvshow tvshow = null;
        try {
            tvshow = query.getSingleResult();
        }catch (Exception ignored){

        }
        return tvshow;
    }

    @Override
    public Tvshow findByTitleWithReviews(String title) {
        TypedQuery<Tvshow> query = entityManager.createQuery(
                "select t from Tvshow t " +
                        "left join fetch t.reviews " +
                        "where t.title = :theTitle ", Tvshow.class);
        query.setParameter("theTitle", title);
        Tvshow tvshow = null;
        try {
            tvshow = query.getSingleResult();
        }catch (Exception ignored){

        }
        return tvshow;
    }

    @Override
    public Tvshow findByIdWithReviews(int id) {
        TypedQuery<Tvshow> query = entityManager.createQuery(
                "select t from Tvshow t " +
                        "left join fetch t.reviews " +
                        "where t.id = :theId ", Tvshow.class);
        query.setParameter("theId", id);
        Tvshow tvshow = null;
        try {
            tvshow = query.getSingleResult();
        }catch (Exception ignored){

        }
        return tvshow;
    }

    @Override
    public Tvshow findByIdWithEverything(int id) {
        TypedQuery<Tvshow> query = entityManager.createQuery(
                "select distinct t from Tvshow t " +
                        "left join fetch t.categories " +
                        "left join fetch t.reviews r " +
                        "where t.id = :theId ", Tvshow.class);
        query.setParameter("theId", id);
        Tvshow tvshow = null;
        try {
            tvshow = query.getSingleResult();
        }catch (Exception ignored){

        }
        return tvshow;
    }


    @Override
    public List<Tvshow> findByAgeRating(int ageRating) {
        /*
        TypedQuery<Tvshow> query = entityManager.createQuery("select t from Tvshow t where ageRating=:theAgeRating", Tvshow.class);
        query.setParameter("theAgeRating", ageRating);
        return query.getResultList();

         */
        return null;
    }

    @Override
    public List<Tvshow> findAllByCategories(Set<Category> categories) {
        TypedQuery<Tvshow> query = entityManager.createQuery(
                "select t from Tvshow t " +
                        "left join fetch t.categories c " +
                        "where c in(:categories) " +
                        "group by t " +
                        "having count (distinct c) = :categoriesCount"
                ,Tvshow.class);
        query.setParameter("categories", categories);
        query.setParameter("categoriesCount", categories.size());

        List<Tvshow> tvshow = new ArrayList<>();
        try {
            tvshow = query.getResultList();
        }catch (Exception ignored){

        }
        return tvshow;
    }

    @Override
    @Transactional
    public void save(Tvshow tvshow) {
        entityManager.persist(tvshow);
    }

    @Override
    @Transactional
    public void update(Tvshow tvshow) {
        entityManager.merge(tvshow);
    }

    @Override
    @Transactional
    public void delete(Tvshow tvshow) {
        entityManager.remove(tvshow);
    }
}
