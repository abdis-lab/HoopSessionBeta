package com.abdisalam.hoopsessionbeta.services;

import com.abdisalam.hoopsessionbeta.dto.SessionPostDto;
import com.abdisalam.hoopsessionbeta.model.SessionPost;
import org.hibernate.Session;

import java.util.List;

public interface SessionPostService {

    void saveSession(SessionPostDto sessionPostDto);

    SessionPost updateSession(SessionPost sessionPost);

    SessionPost findBySessionPostId(Long id);

    SessionPost findByTitle(String title);
    List<SessionPostDto> findAllSessionPost();

    void deleteSession(Long id);

}
