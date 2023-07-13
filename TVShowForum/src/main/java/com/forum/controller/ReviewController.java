package com.forum.controller;

import com.forum.entity.Category;
import com.forum.entity.Review;
import com.forum.entity.Tvshow;
import com.forum.model.WebTvshow;
import com.forum.service.CategoryService;
import com.forum.service.ReviewService;
import com.forum.service.TvshowService;
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

    @Autowired
    public ReviewController(TvshowService tvshowService, ReviewService reviewService){
        this.tvshowService = tvshowService;
        this.reviewService = reviewService;
    }

    /*
    @GetMapping
    public String findAll(Model model){

        return "tvshow/tvshows-list";
    }

     */

    @GetMapping("/{id}")
    public String showReview(@PathVariable("id") int id,Model model){
        Review review = reviewService.findById(id);
        model.addAttribute("review", review);
        model.addAttribute("loggedInUser", SecurityContextHolder.getContext().getAuthentication().getName());
        return "review/review";
    }

    @GetMapping("/showReviewForm")
    public String showTvShowForm(Model model) {

        model.addAttribute("webTvshow", new WebTvshow());

        return "tvshow/tvshow-form";
    }

    @GetMapping("/showReviewFormUpdate")
    public String showTvShowFormUpdate(@RequestParam("tvshowId") int id, Model model) {
        Tvshow tvshow = tvshowService.findById(id);
        if(tvshow== null){
            return "redirect:/tvshows";
        }



        return "tvshow/tvshow-form";
    }

    @PostMapping("/proccessTvshowForm")
    public String proccessTvshowForm(@Valid @ModelAttribute("webTvshow") WebTvshow webTvshow,
                                     @RequestParam("selectedCategories") List<Integer> categoriesIds,
                                     BindingResult bindingResult,
                                     Model model){

        if(bindingResult.hasErrors()){
            return "tvshow/tvshow-form";
        }

        Tvshow existing = tvshowService.findByName(webTvshow.getTitle());
        if(existing != null){
            model.addAttribute("webTvshow", new WebTvshow());
            model.addAttribute("tvshowError", "Tvshow with that title already exists");
            return "tvshow/tvshow-form";
        }

        if (webTvshow.getId()==0)
            tvshowService.save(webTvshow);
        else
            tvshowService.update(webTvshow);

        //redirecting to page with all tvshows
        return "redirect:/tvshows";
    }


    @GetMapping("/delete/{id}")
    public String deleteTvshow(@PathVariable("id") int id){
        Tvshow tvshow = tvshowService.findById(id);
        tvshowService.delete(tvshow);
        return "redirect:/tvshows";

    }
}
