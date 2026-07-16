package it.spring.example.cashcard.shared.events;

import java.time.LocalDateTime;

import org.springframework.modulith.NamedInterface;


/**
 * Evento: Carta creata
 */
@NamedInterface
public record CardCreatedEvent(
        Long cardId,
        Long customerId,
        String cardholderName,
        LocalDateTime timestamp
) implements DomainEvent {
}
