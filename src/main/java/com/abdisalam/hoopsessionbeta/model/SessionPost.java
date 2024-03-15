package com.abdisalam.hoopsessionbeta.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/* This class holds the data for the sessions that the user posts */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "session_post")
public class SessionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionPost_ID;

}
