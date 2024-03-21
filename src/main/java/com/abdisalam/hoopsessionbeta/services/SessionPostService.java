package com.abdisalam.hoopsessionbeta.services;

import com.abdisalam.hoopsessionbeta.dto.SessionPostDto;
import com.abdisalam.hoopsessionbeta.model.SessionPost;
import org.hibernate.Session;

import java.util.List;

public interface SessionPostService {

    void saveSession(SessionPostDto sessionPostDto);



    SessionPost findBySessionPostId(Long id);

    SessionPost findByTitle(String title);
    List<SessionPostDto> findAllSessionPost();
}
