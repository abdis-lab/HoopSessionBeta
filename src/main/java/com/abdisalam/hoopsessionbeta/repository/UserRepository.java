package com.abdisalam.hoopsessionbeta.repository;

import com.abdisalam.hoopsessionbeta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUserName(String username);

    User findUserByUser_Id(Long id);
}
