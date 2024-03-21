package com.abdisalam.hoopsessionbeta.repository;

import com.abdisalam.hoopsessionbeta.model.SessionPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionPostRepository extends JpaRepository<SessionPost, Long> {

    SessionPost findBySessionPostId(Long id);

    SessionPost findByTitle(String title);
}
