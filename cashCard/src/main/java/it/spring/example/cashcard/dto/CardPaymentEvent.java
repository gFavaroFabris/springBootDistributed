package it.spring.example.cashcard.dto;

public record CardPaymentEvent(String cardId, String countId, Double amount) {
}
