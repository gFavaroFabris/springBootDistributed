package it.spring.example.cashcard.customers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import it.spring.example.cashcard.customers.Customer;

/**
 * DTO per la risposta del cliente
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private Long id;
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
    private LocalDate registrationDate;
    private LocalDateTime createdAt;
    private Boolean active;

    /**
     * Costruisce un DTO dalla entità
     */
    public CustomerResponse(Customer entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.phone = entity.getPhone();
        this.dateOfBirth = entity.getDateOfBirth();
        this.taxId = entity.getTaxId();
        this.address = entity.getAddress();
        this.city = entity.getCity();
        this.zipCode = entity.getZipCode();
        this.country = entity.getCountry();
        this.identityDocument = entity.getIdentityDocument();
        this.registrationDate = entity.getRegistrationDate();
        this.createdAt = entity.getCreatedAt();
        this.active = entity.getActive();
    }
}
