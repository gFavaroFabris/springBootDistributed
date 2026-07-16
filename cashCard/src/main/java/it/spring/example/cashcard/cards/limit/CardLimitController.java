package it.spring.example.cashcard.cards.limit;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.spring.example.cashcard.cards.limit.dto.CardLimitCreateRequest;
import it.spring.example.cashcard.cards.limit.dto.CardLimitResponse;

/**
 * <p>Controller per la gestione dei limiti delle carte</p>
 */
@RestController
@RequestMapping("/api/v1/cards/{cardId}/limits")
@RequiredArgsConstructor
public class CardLimitController {

    private final CardLimitService cardLimitService;

    @PostMapping
    public String createCardLimit(@PathVariable Long cardId, @RequestBody CardLimitCreateRequest request, final Model model) {
        request.setCardId(cardId);
        CardLimitResponse response = cardLimitService.createCardLimit(request);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(ApiResponse.success(response, "Limiti carta creati"));
        
        model.addAttribute("cardLimit", response);
        return "cardLimit";
    }

    @GetMapping
    public String getCardLimit(@PathVariable Long cardId, final Model model) {
        CardLimitResponse response = cardLimitService.getCardLimit(cardId);
//        return ResponseEntity.ok(ApiResponse.success(response, "Limiti carta recuperati"));
        model.addAttribute("cardLimit", response);
        return "cardLimit";
    }

    @PutMapping
    public String updateCardLimit(@PathVariable Long cardId, @RequestBody CardLimitCreateRequest request, final Model model) {
        request.setCardId(cardId);
        CardLimitResponse response = cardLimitService.updateCardLimit(cardId, request);
//        return ResponseEntity.ok(ApiResponse.success(response, "Limiti carta aggiornati"));
        model.addAttribute("updateCardLimit", response);
        return "updatedCardLimit";
        
    }

//    @PostMapping("/reset-daily")
//    public String resetDailySpent(@PathVariable Long cardId, final Model model) {
//        cardLimitService.resetDailySpent(cardId);
////        return ResponseEntity.ok(ApiResponse.success(null, "Spesa giornaliera resettata"));
//        model.addAttribute("resetDa", )
//    }
//
//    @PostMapping("/reset-monthly")
//    public String resetMonthlySpent(@PathVariable Long cardId, final Model model) {
//        cardLimitService.resetMonthlySpent(cardId);
////        return ResponseEntity.ok(ApiResponse.success(null, "Spesa mensile resettata"));
//        return "resetMonthlySpent";
//    }
}
