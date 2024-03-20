package com.abdisalam.hoopsessionbeta.dto;


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

    @NotEmpty(message = "Enter a Facility Tittle")
    private String title;

    @NotEmpty
    private String description;


    private BigDecimal cost;

    @NotEmpty
    private MultipartFile image;

    @NotEmpty
    private LocalDateTime startTime;
    @NotEmpty
    private LocalDateTime endTime;


    public SessionPostDto(String title, String description, BigDecimal cost, MultipartFile image) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.image = image;
    }
}
