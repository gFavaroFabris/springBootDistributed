package it.spring.example.cashcard.cards.limit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO per la creazione/aggiornamento dei limiti carta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardLimitCreateRequest {
    private Long cardId;
    private BigDecimal dailyLimit;
    private BigDecimal monthlyLimit;
    private BigDecimal singleTransactionLimit;
    private Boolean withdrawalEnabled;
    private Boolean internationalEnabled;
    private Boolean contactlessEnabled;
}
