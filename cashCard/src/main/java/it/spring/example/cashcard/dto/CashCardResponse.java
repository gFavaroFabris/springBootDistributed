package it.spring.example.cashcard.dto;

public record CashCardResponse(long id, double amount) implements Card {

}
