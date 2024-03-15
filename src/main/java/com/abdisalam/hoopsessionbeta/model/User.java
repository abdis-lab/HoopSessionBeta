package com.abdisalam.hoopsessionbeta.model;

import java.sql.Timestamp;
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
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_ID;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column()
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "Skill_Level")
    private String skill_Level;

    private String time;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "userID", referencedColumnName = "user_ID") },
            inverseJoinColumns = {@JoinColumn(name = "roleID", referencedColumnName = "roleID")}
    )
    private List<Role> rolesList;

    @OneToMany(targetEntity = SessionPost.class)
    private List<SessionPost> sessionPosts;

    public User(String userName,String time, String name, String email, String password, String skill_Level, String city, String state) {
        this.userName = userName;
        this.name = name;
        this.time = time;
        this.email = email;
        this.password = password;
        this.skill_Level = skill_Level;
        this.city = city;
        this.state = state;
    }
}


