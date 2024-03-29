package com.abdisalam.hoopsessionbeta.services;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import com.abdisalam.hoopsessionbeta.dto.UserDto;
import com.abdisalam.hoopsessionbeta.model.Role;
import com.abdisalam.hoopsessionbeta.model.User;
import com.abdisalam.hoopsessionbeta.repository.RoleRepository;
import com.abdisalam.hoopsessionbeta.repository.UserRepository;
import jakarta.persistence.RollbackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of UserService interface.
 * Manages user-related operations, such as saving a user, retrieving a user by email, username, or id, and
 * retrieving all users.
 */
@Service
public class UserServiceImp implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        super();
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
        String formattedDateTime = localDateTime.format(formatter);


        // Saving the user to the database with the getter/setters
        user.setTime(formattedDateTime);
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCity(userDto.getCity());
        user.setUserName(userDto.getUsername());
        user.setState(userDto.getState());
        user.setSkillLevelDisc(userDto.getSkillLevelDisc());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        System.out.println("Password length: " + userDto.getPassword().length());

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExsit();
        }
        user.setRolesList(Arrays.asList(role));
        userRepository.save(user);
    }

    public Role checkRoleExsit(){
        Role role = new Role();

        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User findUserByUserId(Long id) {
        return userRepository.findUserByUserId(id);
    }

    @Override
    public List<UserDto> findAllUser() {
        List<User>users = userRepository.findAll();

        return users.stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());
    }

    public UserDto mapToStudentDto(User user){
        UserDto userDto = new UserDto();

        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        userDto.setCity(user.getCity());
        userDto.setSkillLevelDisc(user.getSkillLevelDisc());
        userDto.setUsername(user.getUserName());
        userDto.setPassword(user.getPassword());

        return userDto;

    }
}
