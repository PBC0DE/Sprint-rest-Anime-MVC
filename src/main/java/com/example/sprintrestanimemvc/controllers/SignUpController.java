package com.example.sprintrestanimemvc.controllers;

import com.example.sprintrestanimemvc.exceptions.UserAlreadyExistsException;
import com.example.sprintrestanimemvc.models.User;
import com.example.sprintrestanimemvc.models.UserLogin;
import com.example.sprintrestanimemvc.models.UserRegistration;
import com.example.sprintrestanimemvc.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
@Controller
@RequestMapping("/")
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home(@AuthenticationPrincipal User user) {
        return "redirect:/anime";
    }

    @GetMapping("/login")
    public String login(Model model, @AuthenticationPrincipal User user) {
        if (user == null) {
            model.addAttribute("userLogin", new UserLogin());
            return "signUp";
        }

        return "redirect:/anime";
    }

    @GetMapping("/signup")
    public String register(Model model, @AuthenticationPrincipal User user) {
        if (user == null) {
            model.addAttribute("userLogin", new UserLogin());
            model.addAttribute("userRegistration", new UserRegistration());
            return "signup";
        }

        return "redirect:/signup";
    }

    @PostMapping("/signup")
    public String register(
            @Valid UserRegistration userRegistration,
            BindingResult result, RedirectAttributes attributes,
            UserLogin userLogin,
            BindingResult result2) {
        if (result.hasErrors()) {
            result2.reject("");
            return "signup";
        }

        if (!userRegistration.getPassword().equals(userRegistration.getConfirmPassword())) {
            result.reject("error.password");
            result2.reject("error.password");
            return "signup";

        }

        try {
            userService.register(userRegistration);
        } catch (UserAlreadyExistsException e) {
            result.reject("error.username");
            result2.reject("error.username");
            return "signup";
        }

        attributes.addFlashAttribute("registerSuccess", "{register.success}");

        return "redirect:/signup";
    }
}
