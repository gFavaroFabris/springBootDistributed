package it.spring.example.cashcard.financing;

import it.spring.example.cashcard.financing.dto.FinancingPlanCreateRequest;
import it.spring.example.cashcard.financing.dto.FinancingPlanResponse;
import it.spring.example.cashcard.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione dei finanziamenti
 */
@Controller
@RequestMapping("/api/v1/financing")
@RequiredArgsConstructor
public class FinancingController {

    private final FinancingService financingService;

    @PostMapping
    public ResponseEntity<ApiResponse<FinancingPlanResponse>> createFinancingPlan(
            @RequestBody FinancingPlanCreateRequest request) {
        FinancingPlanResponse response = financingService.createFinancingPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Piano di finanziamento creato"));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<ApiResponse<FinancingPlanResponse>> getFinancingPlan(@PathVariable Long planId) {
        FinancingPlanResponse response = financingService.getFinancingPlan(planId);
        return ResponseEntity.ok(ApiResponse.success(response, "Piano recuperato"));
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<ApiResponse<List<FinancingPlanResponse>>> getCardPlans(@PathVariable Long cardId) {
        List<FinancingPlanResponse> responses = financingService.getCardFinancingPlans(cardId);
        return ResponseEntity.ok(ApiResponse.success(responses, "Piani della carta"));
    }

    @GetMapping("/customer/{customerId}/active")
    public ResponseEntity<ApiResponse<List<FinancingPlanResponse>>> getActivePlans(@PathVariable Long customerId) {
        List<FinancingPlanResponse> responses = financingService.getActiveFinancingPlans(customerId);
        return ResponseEntity.ok(ApiResponse.success(responses, "Piani attivi"));
    }

    @PostMapping("/{installmentId}/pay")
    public ResponseEntity<ApiResponse<Void>> payInstallment(
            @PathVariable Long installmentId,
            @RequestParam String paymentMethod) {
        financingService.payInstallment(installmentId, paymentMethod);
        return ResponseEntity.ok(ApiResponse.success(null, "Rata pagata"));
    }

    @PostMapping("/{planId}/cancel")
    public ResponseEntity<ApiResponse<Void>> cancelPlan(
            @PathVariable Long planId,
            @RequestParam(required = false) String reason) {
        financingService.cancelFinancingPlan(planId, reason != null ? reason : "Annullamento richiedente");
        return ResponseEntity.ok(ApiResponse.success(null, "Piano annullato"));
    }
}
