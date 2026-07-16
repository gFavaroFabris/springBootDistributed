package it.spring.example.cashcard.shared.events;

import org.springframework.modulith.NamedInterface;

import java.time.LocalDateTime;

/**
 * Evento: Cliente registrato
 */
@NamedInterface
public record CustomerRegisteredEvent(
        Long customerId,
        String email,
        String firstName,
        String lastName,
        LocalDateTime timestamp
) implements DomainEvent {
}
