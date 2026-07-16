package it.spring.example.cashcard.cards.dto;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import jakarta.validation.constraints.NotBlank;


public record CardPaymentCreateRequest(
		Integer cardId, 
		@NotBlank String nominative, 
		String code,
//		@CreatedBy User user,
		@CreatedDate Instant createdDate) {};
