package com.abdisalam.hoopsessionbeta.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/* This class holds the data for the sessions that the user posts */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "sessionPost")
public class SessionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionPostId;

    @Column()
    private String dateTime;

    private int maxPlayers;

    private String description;

    private String title;

    private BigDecimal cost;

    private int duration;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Lob
    private byte[] image;

    private String imageContentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facilityId")
    private Facility facility;

    public SessionPost(byte[] image, String imageContentType, String dateTime, int maxPlayers, String description, String title, BigDecimal cost, int duration, LocalDateTime startTime, LocalDateTime endTime, Facility facility) {
        this.dateTime = dateTime;
        this.maxPlayers = maxPlayers;
        this.description = description;
        this.title = title;
        this.cost = cost;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.facility = facility;
        this.image = image;
        this.imageContentType = imageContentType;
    }
}
