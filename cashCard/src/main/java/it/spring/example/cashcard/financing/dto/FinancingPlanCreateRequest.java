package it.spring.example.cashcard.financing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO per la creazione di un piano di finanziamento
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancingPlanCreateRequest {
    private Long cardId;
    private Long customerId;
    private Long transactionId;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private Integer numberOfInstallments;
}
