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

        SessionPost sessionPost = new SessionPost();
        model.addAttribute("session", sessionPost);


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

    @GetMapping("/sessions/edit/{id}")
    public String editSessionForm(@PathVariable Long id, Model model){

        model.addAttribute("session", sessionPostService.findBySessionPostId(id));
        return "editSession";

    }

    @PostMapping("/sessions/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("session") SessionPostDto sessionPostDto, Model model, Authentication authentication) throws IllegalAccessException {
        SessionPost existingSession = sessionPostService.findBySessionPostId(id);

        if(existingSession == null){
            throw new IllegalAccessException("No session with the " + id + "exists");
        }

        existingSession.setSessionPostId(id);
        existingSession.setTitle(sessionPostDto.getTitle());
        existingSession.setDescription(sessionPostDto.getDescription());
        existingSession.setCost(sessionPostDto.getCost());
        existingSession.setStartTime(sessionPostDto.getStartTime());
        existingSession.setEndTime(sessionPostDto.getEndTime());

        User user = getUserByAuthentication(authentication);
        if(user != null){
            existingSession.setUser(user);
        }

        sessionPostService.updateSession(existingSession);
        return "redirect:/sessionPost";

    }

    @GetMapping("/sessions/{id}")
    //handle detelting sessions
    public String deleteSession(@PathVariable Long id){
        sessionPostService.deleteSession(id);
        return "redirect:/sessionPost";
    }
}
