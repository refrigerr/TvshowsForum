package com.forum.controller;

import com.forum.entity.Category;
import com.forum.entity.Tvshow;
import com.forum.model.WebTvshow;
import com.forum.model.WebUser;
import com.forum.service.CategoryService;
import com.forum.service.CategoryServiceImpl;
import com.forum.service.TvshowService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/tvshows")
public class TvshowController {

    TvshowService tvshowService;
    CategoryService categoryService;

    @Autowired
    public TvshowController(TvshowService tvshowService, CategoryService categoryService){
        this.tvshowService = tvshowService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String findAll(Model model){
        List<Tvshow> tvshows = tvshowService.findAll();
        model.addAttribute("tvshows",tvshows);
        return "tvshow/tvshows-list";
    }

    @GetMapping("/{id}")
    public String showTvshow(@PathVariable("id") int id, Model model){
        Tvshow tvshow = tvshowService.findByIdWithEverything(id);
        model.addAttribute("tvshow",tvshow);
        model.addAttribute("loggedInUser", SecurityContextHolder.getContext().getAuthentication().getName());
        return "tvshow/tvshow";
    }

    @GetMapping("/showTvshowForm")
    public String showTvShowForm(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("webTvshow", new WebTvshow());
        model.addAttribute("categories", categories);


        return "tvshow/tvshow-form";
    }

    @GetMapping("/showTvshowFormUpdate")
    public String showTvShowFormUpdate(@RequestParam("tvshowId") int id, Model model) {
        List<Category> categories = categoryService.findAll();
        Tvshow tvshow = tvshowService.findById(id);
        if(tvshow== null){
            return "redirect:/tvshows";
        }
        WebTvshow webTvshow = new WebTvshow();
        webTvshow.setAgeRating(tvshow.getAgeRating());
        webTvshow.setDescription(tvshow.getDescription());
        webTvshow.setId(tvshow.getId());
        webTvshow.setTitle(tvshow.getTitle());
        webTvshow.setCategories(tvshow.getCategories());
        model.addAttribute("webTvshow", webTvshow);
        model.addAttribute("categories", categories);


        return "tvshow/tvshow-form";
    }

    @PostMapping("/proccessTvshowForm")
    public String proccessTvshowForm(@Valid @ModelAttribute("webTvshow") WebTvshow webTvshow,
                                     @RequestParam(value = "selectedCategories", required = false) List<Integer> categoriesIds,
                                     BindingResult bindingResult,
                                     Model model){

        if(bindingResult.hasErrors()){
            List<Category> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("webTvshow", webTvshow);
            return "tvshow/tvshow-form";
        }

        Tvshow thisTvShow = tvshowService.findById(webTvshow.getId());
        Tvshow existing = tvshowService.findByName(webTvshow.getTitle());

        if(existing != null && !thisTvShow.equals(existing)){
            List<Category> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("webTvshow", new WebTvshow());
            model.addAttribute("tvshowError", "Tvshow with that title already exists");
            return "tvshow/tvshow-form";
        }

        //converting ids into categories
        if(categoriesIds != null){
            for (Integer i :
                    categoriesIds) {
                webTvshow.addCategory(categoryService.findById(i));
            }
        }else {
            webTvshow.setCategories(new HashSet<>());
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
