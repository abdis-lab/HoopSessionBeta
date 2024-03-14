package com.abdisalam.hoopsessionbeta.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_ID;

    @Column(nullable = false, length = 30)
    private String userName;

    @Column()
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 30)
    private String password;

    private List<String> skill_Level;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "attending_user")
    private List<Attending> attendees;

    @OneToMany(targetEntity = SessionPost.class)
    private List<SessionPost> sessionPosts;

    public User(String userName, String name, String email, String password, List<String> skill_Level, String city, String state) {
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.skill_Level = skill_Level;
        this.city = city;
        this.state = state;
    }
}


