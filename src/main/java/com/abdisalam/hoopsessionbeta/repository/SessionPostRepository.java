package com.abdisalam.hoopsessionbeta.repository;

import com.abdisalam.hoopsessionbeta.model.SessionPost;
import com.abdisalam.hoopsessionbeta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionPostRepository extends JpaRepository<SessionPost, Long> {

    SessionPost findBySessionPostId(Long id);

    SessionPost findByTitle(String title);

    List<SessionPost> findByUser(User user);
}
