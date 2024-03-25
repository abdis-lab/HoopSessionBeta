package com.abdisalam.hoopsessionbeta.services;

import com.abdisalam.hoopsessionbeta.dto.SessionPostDto;
import com.abdisalam.hoopsessionbeta.exception.SessionNotFoundException;
import com.abdisalam.hoopsessionbeta.model.SessionPost;
import com.abdisalam.hoopsessionbeta.repository.SessionPostRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implements the SessionPostService interface and provides functionality to manage session posts.
 */
@Service
@RequiredArgsConstructor
public class SessionPostImp implements SessionPostService {

    private static final String UNABLE_TO_STORE_IMAGE_ERROR = "Failed to store image to database";

    private final SessionPostRepository sessionPostRepository;

    @Override
    public void saveSession(SessionPostDto sessionPostDto) {
        SessionPost sessionPost = convertToSessionPost(sessionPostDto);
        sessionPostRepository.save(sessionPost);
    }

    public void saveUpdateSession(SessionPost sessionPost){
        sessionPostRepository.save(sessionPost);
    }

//    @Transactional
//    @Override
//    public SessionPost updateSessionPost(Long id, SessionPostDto sessionPostDto) {
//        SessionPost sessionPost = sessionPostRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("SessionPost not found"));
//        sessionPost.setTitle(sessionPostDto.getTitle());
//        sessionPost.setDescription(sessionPostDto.getDescription());
//        sessionPost.setCost(sessionPostDto.getCost());
//        sessionPost.setStartTime(sessionPostDto.getStartTime());
//        sessionPost.setEndTime(sessionPostDto.getEndTime());
//
//        return sessionPostRepository.save(sessionPost);
//    }

    private SessionPost convertToSessionPost(SessionPostDto sessionPostDto) {
        SessionPost sessionPost = new SessionPost();
        sessionPost.setTitle(sessionPostDto.getTitle());
        sessionPost.setDescription(sessionPostDto.getDescription());
        sessionPost.setCost(sessionPostDto.getCost());
        sessionPost.setStartTime(sessionPostDto.getStartTime());
        sessionPost.setEndTime(sessionPostDto.getEndTime());


        return sessionPost;
    }

    @Override
    public Optional<SessionPost> findById(Long id) {
        return sessionPostRepository.findById(id);
    }

    @Override
    public SessionPost findByTitle(String title) {
        return sessionPostRepository.findByTitle(title);
    }

    @Override
    public List<SessionPostDto> findAllSessionPost() {
        return convertToSessionPostDtoList(sessionPostRepository.findAll());
    }

    private List<SessionPostDto> convertToSessionPostDtoList(List<SessionPost> sessionPosts) {
        return sessionPosts.stream().map(sessionPost -> {
            SessionPostDto sessionPostDto = new SessionPostDto();
            sessionPostDto.setTitle(sessionPost.getTitle());
            sessionPostDto.setDescription(sessionPost.getDescription());
            sessionPostDto.setCost(sessionPost.getCost());
            sessionPostDto.setStartTime(sessionPost.getStartTime());
            sessionPostDto.setEndTime(sessionPost.getEndTime());
            sessionPostDto.setSessionPostId(sessionPost.getSessionPostId());



            return sessionPostDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteSession(Long id) {
        sessionPostRepository.deleteById(id);
    }


    @Override
    public SessionPost get(Long id) throws SessionNotFoundException {
        return sessionPostRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException("Could Not find Any Session with The Id " + id));
    }

}
