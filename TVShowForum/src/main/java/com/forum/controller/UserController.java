package com.forum.controller;

import com.forum.entity.User;
import com.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/user-list";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model){
        User user = userService.findByIdWithReviews(id);

        User loggedInUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        //checks user viewing profile is the same user or if is at least moderator
        boolean isAuthenticatedToModify = user.getUserName().equals(loggedInUser.getUserName()) ||
                SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("MODERATOR"));

        model.addAttribute("user", user);
        model.addAttribute("reviews", user.getReviews());
        model.addAttribute("isAuthenticatedToModify", isAuthenticatedToModify);
        return "user/user";
    }

}
