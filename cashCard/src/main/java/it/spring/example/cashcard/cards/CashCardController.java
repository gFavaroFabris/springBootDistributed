package it.spring.example.cashcard.cards;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione delle carte bancarie
 */
@Controller
@RequestMapping("/api/v1/cards")
public class CashCardController {

    private final CashCardService cashCardService;
    
    public CashCardController(CashCardService cashCardService) {
    	this.cashCardService = cashCardService;
    }
    
    @GetMapping("/{cardId}")
    public String getAllCard(@ModelAttribute Pageable pageable, final Model model) {
        Page<CashCard> cards = cashCardService.getPageableCards(pageable);
        // return ResponseEntity.ok(ApiResponse.success(response, "Carta recuperata"));
        model.addAttribute("cards", cards);
        return "cards";
    }
    
//    @PostMapping
//    public ResponseEntity<ApiResponse<CardResponse>> createCard(@RequestBody CardCreateRequest request) {
//        CardResponse response = cardService.createCard(request);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(ApiResponse.success(response, "Carta creata con successo"));
//    }
//
//    @GetMapping("/{cardId}")
//    public ResponseEntity<ApiResponse<CardResponse>> getCard(@PathVariable Long cardId) {
//        CardResponse response = cardService.getCardById(cardId);
//        return ResponseEntity.ok(ApiResponse.success(response, "Carta recuperata"));
//    }
//
//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<ApiResponse<List<CardResponse>>> getCardsByCustomer(@PathVariable Long customerId) {
//        List<CardResponse> responses = cardService.getCardsByCustomer(customerId);
//        return ResponseEntity.ok(ApiResponse.success(responses, "Carte recuperate"));
//    }
//
//    @GetMapping("/customer/{customerId}/active")
//    public ResponseEntity<ApiResponse<List<CardResponse>>> getActiveCardsByCustomer(@PathVariable Long customerId) {
//        List<CardResponse> responses = cardService.getActiveCardsByCustomer(customerId);
//        return ResponseEntity.ok(ApiResponse.success(responses, "Carte attive recuperate"));
//    }
//
//    @PostMapping("/{cardId}/block")
//    public ResponseEntity<ApiResponse<CardResponse>> blockCard(@PathVariable Long cardId) {
//        CardResponse response = cardService.blockCard(cardId);
//        return ResponseEntity.ok(ApiResponse.success(response, "Carta bloccata"));
//    }
//
//    @PostMapping("/{cardId}/activate")
//    public ResponseEntity<ApiResponse<CardResponse>> activateCard(@PathVariable Long cardId) {
//        CardResponse response = cardService.activateCard(cardId);
//        return ResponseEntity.ok(ApiResponse.success(response, "Carta attivata"));
//    }
//
//    @DeleteMapping("/{cardId}")
//    public ResponseEntity<ApiResponse<Void>> cancelCard(@PathVariable Long cardId) {
//        cardService.cancelCard(cardId);
//        return ResponseEntity.ok(ApiResponse.success(null, "Carta annullata"));
//    }
}
