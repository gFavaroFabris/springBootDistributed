package it.spring.example.cashcard.customers;

import it.spring.example.cashcard.customers.dto.CustomerCreateRequest;
import it.spring.example.cashcard.customers.dto.CustomerResponse;
import it.spring.example.cashcard.shared.events.CustomerRegisteredEvent;
import it.spring.example.cashcard.shared.exception.BusinessException;
import it.spring.example.cashcard.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servizio per la gestione dei clienti
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * Crea un nuovo cliente
     */
    public CustomerResponse createCustomer(CustomerCreateRequest request) {
        // Validazione: email unica
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException("Email già registrata", "DUPLICATE_EMAIL");
        }

        // Validazione: tax ID unico
        if (customerRepository.findByTaxId(request.getTaxId()).isPresent()) {
            throw new BusinessException("Codice fiscale già registrato", "DUPLICATE_TAX_ID");
        }

        Customer customer = Customer.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .dateOfBirth(request.getDateOfBirth())
                .taxId(request.getTaxId())
                .address(request.getAddress())
                .city(request.getCity())
                .zipCode(request.getZipCode())
                .country(request.getCountry())
                .identityDocument(request.getIdentityDocument())
                .registrationDate(LocalDate.now())
                .active(true)
                .build();

        Customer saved = customerRepository.save(customer);
        
        // Pubblica l'evento
        eventPublisher.publishEvent(new CustomerRegisteredEvent(
                saved.getId(),
                saved.getEmail(),
                saved.getFirstName(),
                saved.getLastName(),
                LocalDateTime.now()
        ));
        
        return new CustomerResponse(saved);
    }

    /**
     * Recupera un cliente per ID
     */
    public CustomerResponse getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", customerId));
        return new CustomerResponse(customer);
    }

    /**
     * Recupera un cliente per email
     */
    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "email", email));
        return new CustomerResponse(customer);
    }

    /**
     * Recupera tutti i clienti attivi
     */
    public List<CustomerResponse> getAllActiveCustomers() {
        return customerRepository.findByActive(true).stream()
                .map(CustomerResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Aggiorna un cliente
     */
    public CustomerResponse updateCustomer(Long customerId, CustomerCreateRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", customerId));

        customer.setEmail(request.getEmail());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhone());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setTaxId(request.getTaxId());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        customer.setZipCode(request.getZipCode());
        customer.setCountry(request.getCountry());
        customer.setIdentityDocument(request.getIdentityDocument());

        Customer updated = customerRepository.save(customer);
        return new CustomerResponse(updated);
    }

    /**
     * Disattiva un cliente
     */
    public void deactivateCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", customerId));
        customer.setActive(false);
        customerRepository.save(customer);
    }

    /**
     * Controlla se un cliente esiste
     */
    public boolean existsCustomer(Long customerId) {
        return customerRepository.existsById(customerId);
    }
}
