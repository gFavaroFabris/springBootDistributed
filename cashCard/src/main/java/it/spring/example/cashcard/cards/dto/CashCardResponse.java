package it.spring.example.cashcard.cards.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import it.spring.example.cashcard.shared.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class CashCardResponse implements Card {
    private Long id;
    private Long customerId;
    private String cardNumber;
    private String cardToken;
    private String cardholderName;
    private LocalDate expiryDate;
    private String issuer;
    private String cardType;
    private CardStatus status;
    private BigDecimal dailyLimit;
    private BigDecimal monthlyLimit;
    private BigDecimal currentBalance;
    private LocalDate issuanceDate;
    private LocalDateTime createdAt;
}
