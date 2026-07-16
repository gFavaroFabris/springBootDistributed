package it.spring.example.cashcard.notifications.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO per la risposta di una notifica
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {
    private Long id;
    private String type;
    private String title;
    private String message;
    private String description;
    private Long relatedEntityId;
    private Boolean read;
    private LocalDateTime createdAt;
    private LocalDateTime readAt;
}
