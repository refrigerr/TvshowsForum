package com.forum.controller;

import com.forum.entity.Category;
import com.forum.entity.Review;
import com.forum.entity.Tvshow;
import com.forum.entity.User;
import com.forum.model.WebComment;
import com.forum.model.WebReview;
import com.forum.model.WebTvshow;
import com.forum.service.CategoryService;
import com.forum.service.ReviewService;
import com.forum.service.TvshowService;
import com.forum.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    TvshowService tvshowService;
    ReviewService reviewService;
    UserService userService;

    @Autowired
    public ReviewController(TvshowService tvshowService, ReviewService reviewService, UserService userService){
        this.tvshowService = tvshowService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String showReview(@PathVariable("id") int id,Model model){
        Review review = reviewService.findById(id);
        model.addAttribute("review", review);
        model.addAttribute("loggedInUser", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("webComment",new WebComment());
        return "review/review";
    }

    @GetMapping("/showReviewForm")
    public String showReviewForm(@RequestParam("tvshowId") int tvshowId, Model model) {
        model.addAttribute("webReview",new WebReview());
        model.addAttribute("tvshowId", tvshowId);
        return "review/review-form";
    }

    @GetMapping("/showReviewFormUpdate")
    public String showReviewFormUpdate(@RequestParam("reviewId") int id, Model model) {
        Review review = reviewService.findById(id);
        if(review == null) {
            return "unexpected-error";
        }
        WebReview webReview = new WebReview();
        webReview.setId(review.getId());
        webReview.setTitle(review.getTitle());
        webReview.setDescription(review.getDescription());
        webReview.setRating(review.getRating());

        model.addAttribute("webReview", webReview);
        model.addAttribute("tvshowId", review.getTvshow().getId());

        return "review/review-form";
    }

    @PostMapping("/proccessReviewForm")
    public String proccessReviewForm(@Valid @ModelAttribute("webReview") WebReview webReview,
                                     @RequestParam("tvshowIdToAssign") int tvshowId,
                                     Model model){

        Tvshow tvshow = tvshowService.findById(tvshowId);
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        if(user == null || tvshow == null){
            return "unexpected-error";
        }

        webReview.setTvshow(tvshow);
        webReview.setUser(user);

        if (webReview.getId()==0)
            reviewService.save(webReview);
        else
            reviewService.update(webReview);



        //redirecting to page with all tvshows
        return "redirect:/tvshows/"+tvshowId;
    }


    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") int id){
        Review review = reviewService.findById(id);
        int tvshowId = review.getTvshow().getId();
        reviewService.delete(review);
        return "redirect:/tvshows/"+tvshowId;

    }
}
