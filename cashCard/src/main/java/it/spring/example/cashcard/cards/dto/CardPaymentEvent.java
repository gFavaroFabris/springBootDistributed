package it.spring.example.cashcard.cards.dto;

public record CardPaymentEvent(String cardId, String countId, Double amount) {
}
