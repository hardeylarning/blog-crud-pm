package com.roq.blogcrud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roq.blogcrud.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {
    private int id;
    private String title;
    private String description;
    private User user;
    private LocalDateTime dateTime;
}
