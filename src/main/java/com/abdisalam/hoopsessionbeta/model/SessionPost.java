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
@ToString
@Table(name = "sessionPost")
public class SessionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionPostId;

    private String description;

    private String title;

    private double cost;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facilityId")
    private Facility facility;

    // Write the relationship with the user class here
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public SessionPost(String imageUrl, String description, String title, double cost, LocalDateTime startTime, LocalDateTime endTime, Facility facility, User user) {
        this.description = description;
        this.title = title;
        this.cost = cost;
        this.startTime = startTime;
        this.endTime = endTime;
        this.imageUrl = imageUrl;
        this.facility = facility;
        this.user = user;
    }
}