package com.forum.service;

import com.forum.dao.CommentDao;
import com.forum.entity.Comment;
import com.forum.model.WebComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public void save(WebComment webComment) {
        Comment comment = new Comment();
        comment.setBody(webComment.getBody());
        comment.setReview(webComment.getReview());
        comment.setUser(webComment.getUser());

        commentDao.save(comment);
    }
}
