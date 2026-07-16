package it.spring.example.cashcard.shared.enums;

import org.springframework.modulith.NamedInterface;

/**
 * Stati possibili di una carta bancaria
 */
@NamedInterface
public enum CardStatus {
    ACTIVE("Attiva"),
    INACTIVE("Inattiva"),
    BLOCKED("Bloccata"),
    EXPIRED("Scaduta"),
    SUSPENDED("Sospesa"),
    CANCELLED("Annullata");

    private final String description;

    CardStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
