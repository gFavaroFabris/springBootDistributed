package it.spring.example.cashcard.shared.enums;

import org.springframework.modulith.NamedInterface;

/**
 * Tipologie di transazioni
 */
@NamedInterface
public enum TransactionType {
    PURCHASE("Acquisto"),
    WITHDRAWAL("Prelievo"),
    TRANSFER("Trasferimento"),
    REFUND("Rimborso"),
    PAYMENT("Pagamento"),
    DEPOSIT("Deposito"),
    FEE("Commissione"),
    CASH_ADVANCE("Anticipo in contanti"),
    INTEREST("Interessi");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
