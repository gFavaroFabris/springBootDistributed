package it.spring.example.cashcard.customers;

import it.spring.example.cashcard.customers.dto.CustomerCreateRequest;
import it.spring.example.cashcard.customers.dto.CustomerResponse;
import it.spring.example.cashcard.shared.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione dei clienti
 */
@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
    	this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody CustomerCreateRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Cliente creato con successo"));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomer(@PathVariable Long customerId) {
        CustomerResponse response = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(ApiResponse.success(response, "Cliente recuperato"));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerByEmail(@PathVariable String email) {
        CustomerResponse response = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(ApiResponse.success(response, "Cliente recuperato"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers() {
        List<CustomerResponse> responses = customerService.getAllActiveCustomers();
        return ResponseEntity.ok(ApiResponse.success(responses, "Clienti recuperati"));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(
            @PathVariable Long customerId,
            @RequestBody CustomerCreateRequest request) {
        CustomerResponse response = customerService.updateCustomer(customerId, request);
        return ResponseEntity.ok(ApiResponse.success(response, "Cliente aggiornato"));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse<Void>> deactivateCustomer(@PathVariable Long customerId) {
        customerService.deactivateCustomer(customerId);
        return ResponseEntity.ok(ApiResponse.success(null, "Cliente disattivato"));
    }
}
