package com.abdisalam.hoopsessionbeta;

import com.abdisalam.hoopsessionbeta.model.SessionPost;
import com.abdisalam.hoopsessionbeta.repository.SessionPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class HoopSessionBetaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HoopSessionBetaApplication.class, args);
    }


    @Autowired
    private SessionPostRepository sessionPostRepository;

    @Override
    public void run(String... args) throws Exception {

//        SessionPost sessionPost = new SessionPost();
//        sessionPost.setTitle("Sample Title");
//        sessionPost.setDescription("Sample discription hello world");
//        sessionPost.setCost(23.3);
//        sessionPost.setStartTime(LocalDateTime.now());
//        sessionPost.setEndTime(LocalDateTime.now());
//
//        sessionPostRepository.save(sessionPost);


    }

}
