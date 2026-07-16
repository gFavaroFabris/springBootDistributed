package it.spring.example.cashcard.cards.transaction.dto;

import it.spring.example.cashcard.cards.transaction.Transaction;
import it.spring.example.cashcard.shared.enums.TransactionStatus;
import it.spring.example.cashcard.shared.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO per la risposta della transazione
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private Long id;
    private Long cardId;
    private Long customerId;
    private TransactionType type;
    private TransactionStatus status;
    private BigDecimal amount;
    private BigDecimal fee;
    private BigDecimal totalAmount;
    private String description;
    private String merchantName;
    private String merchantCategory;
    private LocalDateTime transactionDate;
    private String referenceNumber;
    private String authorizationCode;

    /**
     * Costruisce un DTO dalla entità
     */
    public TransactionResponse(Transaction entity) {
        this.id = entity.getId();
        this.cardId = entity.getCardId();
        this.customerId = entity.getCustomerId();
        this.type = entity.getType();
        this.status = entity.getStatus();
        this.amount = entity.getAmount();
        this.fee = entity.getFee();
        this.totalAmount = entity.getTotalAmount();
        this.description = entity.getDescription();
        this.merchantName = entity.getMerchantName();
        this.merchantCategory = entity.getMerchantCategory();
        this.transactionDate = entity.getTransactionDate();
        this.referenceNumber = entity.getReferenceNumber();
        this.authorizationCode = entity.getAuthorizationCode();
    }
}
