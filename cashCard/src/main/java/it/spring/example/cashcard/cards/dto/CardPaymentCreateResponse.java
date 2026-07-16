package it.spring.example.cashcard.cards.dto;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

public record CardPaymentCreateResponse(Integer cardId, String nominative, String code, @CreatedDate Instant createdDate) {

}
