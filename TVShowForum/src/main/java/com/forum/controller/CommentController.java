package com.forum.controller;

import com.forum.entity.Comment;
import com.forum.entity.Review;
import com.forum.entity.Tvshow;
import com.forum.entity.User;
import com.forum.model.WebComment;
import com.forum.model.WebReview;
import com.forum.service.CommentService;
import com.forum.service.ReviewService;
import com.forum.service.TvshowService;
import com.forum.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    ReviewService reviewService;
    CommentService commentService;
    UserService userService;

    @Autowired
    public CommentController(CommentService commentService, ReviewService reviewService, UserService userService){
        this.commentService = commentService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping("/proccessCommentForm")
    public String proccessCommentForm(@Valid @ModelAttribute("webComment") WebComment webComment,
                                     @RequestParam("reviewIdToAssign") int reviewId){

        Review review = reviewService.findById(reviewId);
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        if(user == null || review == null){
            return "unexpected-error";
        }

        webComment.setReview(review);
        webComment.setUser(user);

        if(!webComment.getBody().isBlank()){
            if (webComment.getId()==0)
                commentService.save(webComment);
            else
                commentService.update(webComment);
        }


        return "redirect:/reviews/"+reviewId;
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") int id){
        Comment comment = commentService.findById(id);
        int reviewId = comment.getReview().getId();
        commentService.delete(comment);
        return "redirect:/reviews/"+reviewId;

    }


}
