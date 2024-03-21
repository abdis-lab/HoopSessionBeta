package com.abdisalam.hoopsessionbeta.controller;

import com.abdisalam.hoopsessionbeta.dto.UserDto;
import com.abdisalam.hoopsessionbeta.model.User;
import com.abdisalam.hoopsessionbeta.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User getUser(Authentication authentication) {
        return getOrCreateUser(getUserByAuthentication(authentication));
    }

    @GetMapping("/index")
    public String homePage() {
        return "index";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        if (isUserAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @GetMapping("/profile/{username}")
    public String showUserProfile(@PathVariable("username") String usersName, Model model) {
        User user = userService.findUserByUserName(usersName);

        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("username", user.getUserName());
            return "profile";
        } else {
            return "redirect:/error";
        }
    }

    private User getUserByAuthentication(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return userService.findUserByUserName(username);
        }
        return null;
    }

    private User getOrCreateUser(User user) {
        return user != null ? user : new User();
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    // ...
    @PostMapping("/register/save")
    public String registrationProcess(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model) {
        if (userDto == null) {
            return "redirect:/register?error=userDto missing";
        }
        User exUser = userService.findUserByUserName(userDto.getUsername());
        if (exUser != null && exUser.getUserName() != null && !exUser.getUserName().isEmpty()) {
            bindingResult.rejectValue("email", null, "There is an account registered with that username");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDto);
            return "register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/profile")
    public String showProfileInfo(Model model){
        List<UserDto> userDtoList = userService.findAllUser();
        model.addAttribute("userInfo", userDtoList);

        return "profile";
    }
    // ...
}
