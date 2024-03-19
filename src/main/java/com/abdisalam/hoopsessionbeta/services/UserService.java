package com.abdisalam.hoopsessionbeta.services;

import com.abdisalam.hoopsessionbeta.dto.UserDto;
import com.abdisalam.hoopsessionbeta.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    User findUserByUserName(String username);

    User findUserByUserId(Long id);

    List<UserDto> findAllUser();

}
