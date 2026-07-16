package it.spring.example.cashcard.cards.transaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller per la gestione delle transazioni
 */
@Controller
@RequestMapping("/api/v1/transactions")
public class TransactionController {

//    private final TransactionService transactionService;

//    @PostMapping
//    public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(@RequestBody TransactionCreateRequest request) {
//        TransactionResponse response = transactionService.createTransaction(request);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(ApiResponse.success(response, "Transazione creata con successo"));
//    }
//
//    @GetMapping("/{transactionId}")
//    public ResponseEntity<ApiResponse<TransactionResponse>> getTransaction(@PathVariable Long transactionId) {
//        TransactionResponse response = transactionService.getTransactionById(transactionId);
//        return ResponseEntity.ok(ApiResponse.success(response, "Transazione recuperata"));
//    }
//
//    @GetMapping("/card/{cardId}/history")
//    public ResponseEntity<ApiResponse<Page<TransactionResponse>>> getCardHistory(
//            @PathVariable Long cardId,
//            Pageable pageable) {
//        Page<TransactionResponse> responses = transactionService.getCardTransactionHistory(cardId, pageable);
//        return ResponseEntity.ok(ApiResponse.success(responses, "Storico transazioni carta"));
//    }
//
//    @GetMapping("/customer/{customerId}/history")
//    public ResponseEntity<ApiResponse<Page<TransactionResponse>>> getCustomerHistory(
//            @PathVariable Long customerId,
//            Pageable pageable) {
//        Page<TransactionResponse> responses = transactionService.getCustomerTransactionHistory(customerId, pageable);
//        return ResponseEntity.ok(ApiResponse.success(responses, "Storico transazioni cliente"));
//    }
//
//    @GetMapping("/card/{cardId}/by-status")
//    public ResponseEntity<ApiResponse<Page<TransactionResponse>>> getByStatus(
//            @PathVariable Long cardId,
//            @RequestParam TransactionStatus status,
//            Pageable pageable) {
//        Page<TransactionResponse> responses = transactionService.getTransactionsByStatus(cardId, status, pageable);
//        return ResponseEntity.ok(ApiResponse.success(responses, "Transazioni filtrate"));
//    }
//
//    @GetMapping("/card/{cardId}/by-type")
//    public ResponseEntity<ApiResponse<Page<TransactionResponse>>> getByType(
//            @PathVariable Long cardId,
//            @RequestParam TransactionType type,
//            Pageable pageable) {
//        Page<TransactionResponse> responses = transactionService.getTransactionsByType(cardId, type, pageable);
//        return ResponseEntity.ok(ApiResponse.success(responses, "Transazioni filtrate"));
//    }
//
//    @GetMapping("/card/{cardId}/date-range")
//    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getByDateRange(
//            @PathVariable Long cardId,
//            @RequestParam LocalDateTime startDate,
//            @RequestParam LocalDateTime endDate) {
//        List<TransactionResponse> responses = transactionService.getTransactionsByDateRange(cardId, startDate, endDate);
//        return ResponseEntity.ok(ApiResponse.success(responses, "Transazioni per periodo"));
//    }
//
//    @PostMapping("/{transactionId}/reverse")
//    public ResponseEntity<ApiResponse<TransactionResponse>> reverseTransaction(
//            @PathVariable Long transactionId,
//            @RequestParam(required = false) String reason) {
//        TransactionResponse response = transactionService.reverseTransaction(transactionId, reason);
//        return ResponseEntity.ok(ApiResponse.success(response, "Transazione stornata"));
//    }
//
//    @GetMapping("/card/{cardId}/pending-count")
//    public ResponseEntity<ApiResponse<Long>> getPendingCount(@PathVariable Long cardId) {
//        long count = transactionService.getPendingTransactionCount(cardId);
//        return ResponseEntity.ok(ApiResponse.success(count, "Conteggio transazioni in sospeso"));
//    }
}
