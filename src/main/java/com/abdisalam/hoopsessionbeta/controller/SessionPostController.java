package com.abdisalam.hoopsessionbeta.controller;

import com.abdisalam.hoopsessionbeta.dto.SessionPostDto;
import com.abdisalam.hoopsessionbeta.dto.UserDto;
import com.abdisalam.hoopsessionbeta.model.SessionPost;
import com.abdisalam.hoopsessionbeta.model.User;
import com.abdisalam.hoopsessionbeta.services.SessionPostService;
import com.abdisalam.hoopsessionbeta.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SessionPostController {
    private final SessionPostService sessionPostService;
    private final UserService userService;

    private static final String SESSIONS_POST_KEY = "sessionsPost";

    @Autowired
    public SessionPostController(SessionPostService sessionPostService, UserService userService) {
        this.sessionPostService = sessionPostService;
        this.userService = userService;
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


    @GetMapping("/sessionPost")
    public String sessionForm(Model model, Authentication authentication) {
        List<SessionPostDto> sessionPostDtoList = sessionPostService.findAllSessionPost();
        model.addAttribute("sessionPostDtoList", sessionPostDtoList);
        SessionPostDto sessionPostDto = new SessionPostDto();

        User user = getUserByAuthentication(authentication);
        model.addAttribute("user", user);


        model.addAttribute(SESSIONS_POST_KEY, sessionPostDto);
        return "sessionPost";
    }

    @PostMapping("/sessionPost/save")
    public String addSession(@Valid @ModelAttribute(SESSIONS_POST_KEY) SessionPostDto sessionPostDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "sessionPost";
        }

        sessionPostService.saveSession(sessionPostDto);

        model.addAttribute(SESSIONS_POST_KEY, new SessionPostDto());
//        model.addAttribute("sessionPostDtoList", sessionPostService.findAllSessionPost());
        return "redirect:/sessionPost";
    }
}
