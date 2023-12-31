package com.forum.dao;

import com.forum.entity.Comment;
import com.forum.entity.Review;

public interface CommentDao {

    Comment findById(int id);
    void save(Comment comment);
    void update(Comment comment);
    void delete(Comment comment);
}
