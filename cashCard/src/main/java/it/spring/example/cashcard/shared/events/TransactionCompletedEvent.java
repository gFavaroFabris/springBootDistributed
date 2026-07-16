package it.spring.example.cashcard.shared.events;

import org.springframework.modulith.NamedInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Evento: Transazione completata
 */
@NamedInterface
public record TransactionCompletedEvent(
        Long transactionId,
        Long cardId,
        Long customerId,
        String description,
        BigDecimal amount,
        LocalDateTime timestamp
) implements DomainEvent {
}
