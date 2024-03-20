package com.abdisalam.hoopsessionbeta.services;

import com.abdisalam.hoopsessionbeta.dto.SessionPostDto;
import com.abdisalam.hoopsessionbeta.model.SessionPost;
import com.abdisalam.hoopsessionbeta.repository.SessionPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class SessionPostImp implements SessionPostService{

    private SessionPostRepository sessionPostRepository;

    @Autowired
    public SessionPostImp(SessionPostRepository sessionPostRepository){
        this.sessionPostRepository = sessionPostRepository;
    }

    @Override
    public void saveSession(SessionPostDto sessionPostDto) {

        SessionPost sessionPost = new SessionPost();

        sessionPost.setTitle(sessionPostDto.getTitle());
        sessionPost.setDescription(sessionPostDto.getDescription());
        sessionPost.setCost(sessionPostDto.getCost());
        sessionPost.setStartTime(sessionPostDto.getStartTime());
        sessionPost.setEndTime(sessionPostDto.getEndTime());

        MultipartFile file = sessionPostDto.getImage();
        try{
            if(file != null && !file.isEmpty()){

                sessionPost.setImage(file.getBytes());
                sessionPost.setImageContentType(file.getContentType());
            }
        }catch(IOException e){
            throw new RuntimeException("Faild to store image to database");
        }
    }

    @Override
    public List<SessionPostDto> findAllSessionPost() {
        return sessionPostRepository.findAll().stream().map(sessionPost -> {
            SessionPostDto dto = new SessionPostDto();

            dto.setTitle(sessionPost.getTitle());
            dto.setDescription(sessionPost.getDescription());
            dto.setCost(sessionPost.getCost());
            dto.setStartTime(sessionPost.getStartTime());
            dto.setEndTime(sessionPost.getEndTime());


            return dto;
        }).collect(Collectors.toList());
    }
}
