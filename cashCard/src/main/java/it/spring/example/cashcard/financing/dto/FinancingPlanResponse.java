package it.spring.example.cashcard.financing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import it.spring.example.cashcard.financing.FinancingPlan;

/**
 * DTO per la risposta del piano di finanziamento
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancingPlanResponse {
    private Long id;
    private Long cardId;
    private Long customerId;
    private BigDecimal originalAmount;
    private BigDecimal totalAmount;
    private BigDecimal remainingAmount;
    private BigDecimal monthlyPayment;
    private BigDecimal interestRate;
    private Integer numberOfInstallments;
    private Integer paidInstallments;
    private String status;
    private LocalDate startDate;
    private LocalDate nextPaymentDate;
    private LocalDate endDate;

    /**
     * Costruisce un DTO dalla entità
     */
    public FinancingPlanResponse(FinancingPlan entity) {
        this.id = entity.getId();
        this.cardId = entity.getCardId();
        this.customerId = entity.getCustomerId();
        this.originalAmount = entity.getOriginalAmount();
        this.totalAmount = entity.getTotalAmount();
        this.remainingAmount = entity.getRemainingAmount();
        this.monthlyPayment = entity.getMonthlyPayment();
        this.interestRate = entity.getInterestRate();
        this.numberOfInstallments = entity.getNumberOfInstallments();
        this.paidInstallments = entity.getPaidInstallments();
        this.status = entity.getStatus();
        this.startDate = entity.getStartDate();
        this.nextPaymentDate = entity.getNextPaymentDate();
        this.endDate = entity.getEndDate();
    }
}
