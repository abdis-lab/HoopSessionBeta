package com.abdisalam.hoopsessionbeta.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SessionPostDto {

    private Long sessionPostId;

    @NotEmpty(message = "Enter a Facility Tittle")
    private String title;

    @NotEmpty
    private String description;


    private double cost;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


    public SessionPostDto(String title, String description, double cost, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
