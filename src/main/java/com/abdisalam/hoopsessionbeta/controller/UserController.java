package com.abdisalam.hoopsessionbeta.controller;


import com.abdisalam.hoopsessionbeta.dto.UserDto;
import com.abdisalam.hoopsessionbeta.model.User;
import com.abdisalam.hoopsessionbeta.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.internal.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;



    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }



    @GetMapping("/index")
    public String homePage(){
        return "index";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request){
        if(areYouAuthenticated()){
            return "redirect:/index";
        }

        return "login";
    }


    @GetMapping("/register")
    public String register(Model model){

        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "register";
    }

    @GetMapping("/sessions")
    public String sessionPost(){
        return "sessionPost";
    }

    @GetMapping("/profile/{username}")
    public String userProfile(@PathVariable("username") String usersName, Model model){

        User user = userService.findUserByUserName(usersName);


        if(user != null){
            model.addAttribute("user", user);
            return "profile";
        }else{
            return "redirect:/error";
        }
    }

    @ModelAttribute("currentUser")
    public String currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()){
            String username = authentication.getName();
            User user = userService.findUserByUserName(username);
            if(user != null){
                String urlFriendlyName = makeUrlFriendly(user.getName());
                return "profile/" + urlFriendlyName;
            }
        }

        return null;
    }

    private String makeUrlFriendly(String name){
        return name.toLowerCase().replace(" ", "-");
    }



    private boolean areYouAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }


    @PostMapping("/register/save")
    public String registrationProcess(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model){

        User exUser = userService.findUserByUserName(userDto.getUsername());

        if(exUser != null && exUser.getUserName() != null && !exUser.getUserName().isEmpty()){
            bindingResult.rejectValue("email", null, "There is an account registered with that username");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user", userDto);

            return "register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }


}
