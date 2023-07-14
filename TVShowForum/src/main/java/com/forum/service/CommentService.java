package com.forum.service;

import com.forum.entity.Comment;
import com.forum.model.WebComment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();
    void save(WebComment webComment);
    void update(WebComment webComment);
    void delete(Comment comment);

    Comment findById(int id);
}
