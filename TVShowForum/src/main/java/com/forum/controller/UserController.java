package com.forum.controller;

import com.forum.entity.User;
import com.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("loggedInUser", userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
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
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("isAuthenticatedToModify", isAuthenticatedToModify);
        return "user/user";
    }

    @GetMapping("/showChangePassword")
    public String changePassword(){
        return "user/user-change-password-form";
    }

    @PostMapping("/processChangePassword")
    public String processChangePassword(@RequestParam("oldPassword") String oldPassword,
                                        @RequestParam("newPassword") String newPassword,
                                        Model model){


        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        if(!passwordEncoder.matches(oldPassword, user.getPassword())){
            model.addAttribute("passwordError","Incorrect current password");
            return "user/user-change-password-form";
        }
        if(newPassword.length()<3 || newPassword.isBlank()){
            model.addAttribute("passwordError","Password minimum length is 3 and can't be blank");
            return "user/user-change-password-form";
        }

        if(newPassword.equals(oldPassword)){
            model.addAttribute("passwordError","New password can't be the same as current password");
            return "user/user-change-password-form";
        }
        String encodedNewPassword = passwordEncoder.encode(newPassword);

        user.setPassword(encodedNewPassword);
        userService.update(user);
        return "redirect:/users/"+user.getId();
    }

}
