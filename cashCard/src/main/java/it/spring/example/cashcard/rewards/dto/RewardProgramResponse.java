package it.spring.example.cashcard.rewards.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import it.spring.example.cashcard.rewards.RewardProgram;

/**
 * DTO per la risposta del programma di reward
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardProgramResponse {
    private Long id;
    private Long customerId;
    private BigDecimal currentPoints;
    private BigDecimal currentCashback;
    private BigDecimal lifetimeCashback;
    private BigDecimal lifetimePoints;
    private Integer tier;
    private String tierName;
    private Boolean active;
    private LocalDateTime enrollmentDate;
    private LocalDateTime updatedAt;

    /**
     * Costruisce un DTO dalla entità
     */
    public RewardProgramResponse(RewardProgram entity) {
        this.id = entity.getId();
        this.customerId = entity.getCustomerId();
        this.currentPoints = entity.getCurrentPoints();
        this.currentCashback = entity.getCurrentCashback();
        this.lifetimeCashback = entity.getLifetimeCashback();
        this.lifetimePoints = entity.getLifetimePoints();
        this.tier = entity.getTier();
        this.tierName = getTierName(entity.getTier());
        this.active = entity.getActive();
        this.enrollmentDate = entity.getEnrollmentDate();
        this.updatedAt = entity.getUpdatedAt();
    }

    /**
     * Converte il numero del tier al nome
     */
    private static String getTierName(Integer tier) {
        return switch (tier) {
            case 2 -> "Silver";
            case 3 -> "Gold";
            case 4 -> "Platinum";
            default -> "Bronze";
        };
    }
}
