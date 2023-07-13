package com.forum.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tvshow")
public class Tvshow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "age_rating")
    private int ageRating;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "tvshows_categories",
            joinColumns = @JoinColumn(name = "tvshow_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @OneToMany(mappedBy = "tvshow", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> reviews;

    public Tvshow() {

    }

    public Tvshow(String title, String description, int ageRating, Set<Category> categories, Set<Review> reviews) {
        this.title = title;
        this.description = description;
        this.ageRating = ageRating;
        this.categories = categories;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public void addCategory(Category category){
        if(categories == null){
            categories = new HashSet<>();
        }
        categories.add(category);
    }

    public void addReview(Review review){
        if(reviews == null){
            reviews = new HashSet<>();
        }
        reviews.add(review);
    }

    public String displayCategories(){
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        for (Category c : categories) {
            if(!first){
                stringBuilder.append(", ");
            }
            stringBuilder.append(c.getName());

            if (first){
                first = false;
            }

        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Tvshow{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ageRating=" + ageRating +
                '}';
    }
}
