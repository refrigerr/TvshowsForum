package com.forum.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "tvshow_id")
    private Tvshow tvshow;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private int rating;

    @OneToMany(mappedBy = "review",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Review(Tvshow tvshow, User user, String title, String description, int rating, List<Comment> comments) {
        this.tvshow = tvshow;
        this.user = user;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.comments = comments;
    }

    public Review() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tvshow getTvshow() {
        return tvshow;
    }

    public void setTvshow(Tvshow tvshow) {
        this.tvshow = tvshow;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", tvshow=" + tvshow.getTitle() +
                ", user=" + user.getUserName() +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
