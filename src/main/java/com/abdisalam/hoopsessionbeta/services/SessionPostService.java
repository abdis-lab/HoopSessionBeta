package com.abdisalam.hoopsessionbeta.services;

import com.abdisalam.hoopsessionbeta.dto.SessionPostDto;

import java.util.List;

public interface SessionPostService {

    void saveSession(SessionPostDto sessionPostDto);

    List<SessionPostDto> findAllSessionPost();
}
