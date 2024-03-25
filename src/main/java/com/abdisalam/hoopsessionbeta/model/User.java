package com.abdisalam.hoopsessionbeta.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents a User in the system.
 * The User class is used to store information about a user, such as their username, name, email, password,
 * skill level, time, city, and state. It also contains a list of roles associated with the user and a list of
 * session posts created by the user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "rolesList")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column()
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "Skill_Level")
    private String skillLevelDisc;

    private String time;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "userId") },
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "roleId")}
    )
    private List<Role> rolesList;

    @OneToMany(targetEntity = SessionPost.class, mappedBy = "user")
    private List<SessionPost> sessionPosts;

    public User(String userName,List<SessionPost> sessionPosts, String time, String name, String email, String password,List<Role> rolesList, String skillLevelDisc, String city, String state) {
        this.userName = userName;
        this.name = name;
        this.time = time;
        this.email = email;
        this.password = password;
        this.skillLevelDisc = skillLevelDisc;
        this.city = city;
        this.state = state;
        this.rolesList = rolesList;
        this.sessionPosts = new ArrayList<>();
    }
}


