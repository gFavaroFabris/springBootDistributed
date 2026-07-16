package it.spring.example.cashcard.cards.transaction.dto;

import it.spring.example.cashcard.shared.enums.TransactionStatus;
import it.spring.example.cashcard.shared.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO per la creazione di una transazione
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCreateRequest {
    private Long cardId;
    private Long customerId;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal fee;
    private String description;
    private String merchantName;
    private String merchantCategory;
    private String referenceNumber;
}
