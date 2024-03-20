package com.abdisalam.hoopsessionbeta.controller;


import com.abdisalam.hoopsessionbeta.dto.SessionPostDto;
import com.abdisalam.hoopsessionbeta.services.SessionPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sessions")
public class SessionPostController {

    private final SessionPostService sessionPostService;

    @Autowired
    public SessionPostController(SessionPostService sessionPostService){
        this.sessionPostService = sessionPostService;
    }


    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addSession(
            @ModelAttribute SessionPostDto sessionPostDto,
            RedirectAttributes redirectAttributes,
            @RequestHeader(value = "X-Requested-with", required = false) String requestedWith
    ){
        try{
            sessionPostService.saveSession(sessionPostDto);
            redirectAttributes.addFlashAttribute("message", "Session added successfully");

            if("XMLHttpRequest".equals(requestedWith)){
                return ResponseEntity.ok(sessionPostDto);
            }else{
                redirectAttributes.addFlashAttribute("message", "Session added successfully");
                return ResponseEntity.ok("redirect:/sessions");
            }


        }catch(Exception e){
            if("XMLHttpRequest".equals(requestedWith)){
                return ResponseEntity.badRequest().body("Error adding session");
            }else {
                redirectAttributes.addFlashAttribute("errorMessage", "Error adding session");
                return ResponseEntity.ok("redirect:/sessions");
            }

        }

    }


}
