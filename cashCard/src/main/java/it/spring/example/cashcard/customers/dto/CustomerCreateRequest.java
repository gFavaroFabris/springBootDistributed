package it.spring.example.cashcard.customers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO per la creazione/aggiornamento di un cliente
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCreateRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private LocalDate dateOfBirth;
    private String taxId;
    private String address;
    private String city;
    private String zipCode;
    private String country;
    private String identityDocument;
}
