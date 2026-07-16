package it.spring.example.cashcard.cards.limit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import it.spring.example.cashcard.cards.limit.CardLimit;

/**
 * DTO per la risposta dei limiti carta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardLimitResponse {
    private Long id;
    private Long cardId;
    private BigDecimal dailyLimit;
    private BigDecimal dailySpent;
    private BigDecimal monthlyLimit;
    private BigDecimal monthlySpent;
    private BigDecimal singleTransactionLimit;
    private Boolean withdrawalEnabled;
    private Boolean internationalEnabled;
    private Boolean contactlessEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Costruisce un DTO dalla entità
     */
    public CardLimitResponse(CardLimit entity) {
        this.id = entity.getId();
        this.cardId = entity.getCardId();
        this.dailyLimit = entity.getDailyLimit();
        this.dailySpent = entity.getDailySpent();
        this.monthlyLimit = entity.getMonthlyLimit();
        this.monthlySpent = entity.getMonthlySpent();
        this.singleTransactionLimit = entity.getSingleTransactionLimit();
        this.withdrawalEnabled = entity.getWithdrawalEnabled();
        this.internationalEnabled = entity.getInternationalEnabled();
        this.contactlessEnabled = entity.getContactlessEnabled();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
