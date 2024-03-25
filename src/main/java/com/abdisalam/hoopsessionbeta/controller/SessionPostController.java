package com.abdisalam.hoopsessionbeta.controller;

import com.abdisalam.hoopsessionbeta.dto.SessionPostDto;
import com.abdisalam.hoopsessionbeta.dto.UserDto;
import com.abdisalam.hoopsessionbeta.exception.SessionNotFoundException;
import com.abdisalam.hoopsessionbeta.model.SessionPost;
import com.abdisalam.hoopsessionbeta.model.User;
import com.abdisalam.hoopsessionbeta.services.SessionPostImp;
import com.abdisalam.hoopsessionbeta.services.SessionPostService;
import com.abdisalam.hoopsessionbeta.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        if (bindingResult.hasErrors()) {
            return "sessionPost";
        }

        sessionPostService.saveSession(sessionPostDto);

        model.addAttribute(SESSIONS_POST_KEY, new SessionPostDto());
//        model.addAttribute("sessionPostDtoList", sessionPostService.findAllSessionPost());
        return "redirect:/sessionPost";
    }

//    @GetMapping("/sessions/edit/{id}")
//    public String editSessionForm(@PathVariable Long id, Model model){
//        SessionPost sessionPost = sessionPostService.findBySessionPostId(id);
//        SessionPostDto sessionPostDto = convertToDto(sessionPost);
//
//        model.addAttribute("session", sessionPostDto);
//        return "editSession";
//
//    }
//
//    @PostMapping("/sessions/update")
//    public String updateSession(@PathVariable Long id, @ModelAttribute("session") SessionPostDto sessionPostDto, RedirectAttributes redirectAttributes, Model model, Authentication authentication) throws IllegalAccessException {
//        sessionPostService.updateSessionPost(id, sessionPostDto);
//        redirectAttributes.addFlashAttribute("message", "Session post updated successfully!");
//        return "redirect:/sessionPost";
//    }
//
//
//    private void updateSessionPostFromDto(SessionPostDto sessionPostDto, SessionPost sessionPost) {
//        sessionPost.setTitle(sessionPostDto.getTitle());
//        sessionPost.setDescription(sessionPostDto.getDescription());
//        sessionPost.setCost(sessionPostDto.getCost());
//        sessionPost.setStartTime(sessionPostDto.getStartTime());
//        sessionPost.setEndTime(sessionPostDto.getEndTime());
//        // Handle other fields as necessary, including relationships or complex types
//    }
//
    @GetMapping("/sessions/{id}")
    //handle deleting sessions
    public String deleteSession(@PathVariable Long id) {
        sessionPostService.deleteSession(id);
        return "redirect:/sessionPost";
    }

//

//
//
//
//
    @GetMapping("/sessions/edit/{id}")
    public String getSession(@PathVariable("id") Long id,@Valid @ModelAttribute("session") SessionPostDto sessionPostDto, Model model, RedirectAttributes redirectAttributes) {
        try{
            SessionPost sessionPost = sessionPostService.get(id);
            model.addAttribute("session", new SessionPostDto());
            return "editSession";
        }catch(SessionNotFoundException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "The Session Has been saved successfully.");
            return "redirect:/sessionPost";
        }
    }



}















