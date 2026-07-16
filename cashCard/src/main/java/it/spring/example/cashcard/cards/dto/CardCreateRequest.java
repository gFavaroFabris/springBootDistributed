package it.spring.example.cashcard.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO per la creazione di una carta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardCreateRequest {
    private Long customerId;
    private String cardNumber;
    private String cardholderName;
    private LocalDate expiryDate;
    private String cvv;
    private String issuer;
    private String cardType;
    private BigDecimal dailyLimit;
    private BigDecimal monthlyLimit;
    private LocalDate issuanceDate;
}
