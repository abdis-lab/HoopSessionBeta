package com.abdisalam.hoopsessionbeta.services;

import com.abdisalam.hoopsessionbeta.dto.SessionPostDto;
import com.abdisalam.hoopsessionbeta.model.SessionPost;
import com.abdisalam.hoopsessionbeta.repository.SessionPostRepository;
import com.abdisalam.hoopsessionbeta.services.SessionPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    private SessionPost convertToSessionPost(SessionPostDto sessionPostDto) {
        SessionPost sessionPost = new SessionPost();
        sessionPost.setTitle(sessionPostDto.getTitle());
        sessionPost.setDescription(sessionPostDto.getDescription());
        sessionPost.setCost(sessionPostDto.getCost());
        sessionPost.setStartTime(sessionPostDto.getStartTime());
        sessionPost.setEndTime(sessionPostDto.getEndTime());
        MultipartFile file = sessionPostDto.getImage();

        if (file != null && !file.isEmpty()) {
            try {
                sessionPost.setImage(file.getBytes());
                sessionPost.setImageContentType(file.getContentType());
            } catch (IOException e) {
                throw new RuntimeException(UNABLE_TO_STORE_IMAGE_ERROR);
            }
        }

        return sessionPost;
    }

    @Override
    public SessionPost findBySessionPostId(Long id) {
        return sessionPostRepository.findBySessionPostId(id);
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

            return sessionPostDto;
        }).collect(Collectors.toList());
    }
}
