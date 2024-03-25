package com.abdisalam.hoopsessionbeta.services;

import com.abdisalam.hoopsessionbeta.dto.SessionPostDto;
import com.abdisalam.hoopsessionbeta.exception.SessionNotFoundException;
import com.abdisalam.hoopsessionbeta.model.SessionPost;

import java.util.List;
import java.util.Optional;

public interface SessionPostService {

    void saveSession(SessionPostDto sessionPostDto);


    void saveUpdateSession(SessionPost sessionPost);
//    SessionPost updateSessionPost(Long id, SessionPostDto sessionPostDto);

    Optional<SessionPost> findById(Long id);

    SessionPost get(Long id)throws SessionNotFoundException;

    SessionPost findByTitle(String title);
    List<SessionPostDto> findAllSessionPost();

    void deleteSession(Long id);

}
