package it.spring.example.cashcard.config;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import it.spring.example.cashcard.cards.CashCard;
import it.spring.example.cashcard.cards.CashCardRepository;
import it.spring.example.cashcard.customers.Customer;
import it.spring.example.cashcard.customers.CustomerRepository;
import it.spring.example.cashcard.shared.enums.CardStatus;

@Component
@Profile({"dev", "local"})
public class DataSeeder implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CashCardRepository cardRepository;

    public DataSeeder(CustomerRepository customerRepository, CashCardRepository cardRepository) {
		this.customerRepository = customerRepository;
		this.cardRepository = cardRepository;
	}

	@Override
    public void run(String... args) {
        if (customerRepository.count() > 0) return;

        Customer mario = customerRepository.save(Customer.builder()
                .email("mario.rossi@example.com")
                .firstName("Mario")
                .lastName("Rossi")
                .phone("+39 333 1234567")
                .dateOfBirth(LocalDate.of(1985, 4, 12))
                .taxId("RSSMRA85D12H501A")
                .address("Via Roma 1")
                .city("Venezia")
                .zipCode("30100")
                .country("IT")
                .identityDocument("CI-AA1234567")
                .registrationDate(LocalDate.now())
                .active(true)
                .build());
        
        final String fullName = String.format("%s %s", mario.getFirstName(), mario.getLastName());

        cardRepository.save(CashCard.builder()
                .customerId(mario.getId())
                .cardNumber("4000000000000001")
                .cardToken("TOKEN_AAA111")
                .cardholderName(fullName)
                .expiryDate(LocalDate.now().plusYears(4))
                .cvv("123")
                .issuer("Visa")
                .cardType("DEBIT")
                .status(CardStatus.ACTIVE)
                .dailyLimit(new BigDecimal("500.00"))
                .monthlyLimit(new BigDecimal("5000.00"))
                .issuanceDate(LocalDate.now())
                .build());
        
        cardRepository.save(CashCard.builder()
                .customerId(mario.getId())
                .cardNumber("4000000000000002")
                .cardToken("TOKEN_AAA112")
                .cardholderName(fullName)
                .expiryDate(LocalDate.now().plusYears(5))
                .cvv("124")
                .issuer("Visa")
                .cardType("DEBIT")
                .status(CardStatus.ACTIVE)
                .dailyLimit(new BigDecimal("1000.00"))
                .monthlyLimit(new BigDecimal("10000.00"))
                .issuanceDate(LocalDate.now())
                .build());
    }
}
