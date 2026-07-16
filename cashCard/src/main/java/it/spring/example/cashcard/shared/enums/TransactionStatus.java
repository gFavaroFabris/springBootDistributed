package it.spring.example.cashcard.shared.enums;

import org.springframework.modulith.NamedInterface;

/**
 * Stati di una transazione
 */
@NamedInterface
public enum TransactionStatus {
    PENDING("In sospeso"),
    COMPLETED("Completata"),
    FAILED("Fallita"),
    REVERSED("Stornata"),
    DECLINED("Rifiutata");

    private final String description;

    TransactionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
