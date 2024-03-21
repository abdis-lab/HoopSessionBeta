package com.abdisalam.hoopsessionbeta.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Long userDto_ID;

    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "UserName should not be empty")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String LastName;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;


    private String skillLevelDisc;

    public UserDto(String email, String username, String password, String firstName, String lastName, String city, String state, String skillLevelDisc) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        LastName = lastName;
        this.city = city;
        this.state = state;
        this.skillLevelDisc = skillLevelDisc;
    }
}
