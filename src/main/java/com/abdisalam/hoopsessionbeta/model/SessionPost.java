package com.abdisalam.hoopsessionbeta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "sessionPost")
public class SessionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionPostId;
    public Long setSessionPostId(Long id){
        return sessionPostId;
    }

    private String description;

    private String title;

    private double cost;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facilityId")
    private Facility facility;

    // Write the relationship with the user class here
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public SessionPost(String imageUrl, String description, String title, double cost, LocalDateTime startTime, LocalDateTime endTime, Facility facility, User user) {
        this.description = description;
        this.title = title;
        this.cost = cost;
        this.startTime = startTime;
        this.endTime = endTime;
        this.facility = facility;
        this.user = user;
    }

    public Long getSessionPostId() {
        return sessionPostId;
    }
}