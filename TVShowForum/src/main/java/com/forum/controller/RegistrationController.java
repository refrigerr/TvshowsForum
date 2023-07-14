package com.forum.controller;

import com.forum.entity.User;
import com.forum.service.UserService;
import com.forum.model.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("webUser", new WebUser());

        return "register/registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("webUser") WebUser webUser,
            BindingResult bindingResult,
            HttpSession session, Model model) {

        String userName = webUser.getUserName();

        // form validation
        if (bindingResult.hasErrors()){
            return "register/registration-form";
        }

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
            model.addAttribute("webUser", new WebUser());
            model.addAttribute("registrationError", "Account with this username already exists");

            return "register/registration-form";
        }
        existing = userService.findByEmail(webUser.getEmail());

        if (existing != null){
            model.addAttribute("webUser", new WebUser());
            model.addAttribute("registrationError", "Account with this email already exists");

            return "register/registration-form";
        }

        // create user account and store in the database
        userService.save(webUser);

        // place user in the web http session for later use
        session.setAttribute("user", webUser);

        return "register/registration-confirmation";
    }

}
