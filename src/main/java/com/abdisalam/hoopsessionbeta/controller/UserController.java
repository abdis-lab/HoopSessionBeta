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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private boolean areYouAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }


    @PostMapping("/register/save")
    public String registrationProcess(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model){

        User exUser = userService.findUserByUserName(userDto.getUsername());

        if(exUser != null && exUser.getUserName() != null && !exUser.getUserName().isEmpty()){
            bindingResult.rejectValue("email", null, "There is an account registered with that account");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("user", userDto);

            return "register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }


}
