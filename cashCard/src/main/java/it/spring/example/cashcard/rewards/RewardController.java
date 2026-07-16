package it.spring.example.cashcard.rewards;

import it.spring.example.cashcard.rewards.dto.RewardProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Controller per la gestione dei rewards
 */
@RestController
@RequestMapping("/api/v1/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    @PostMapping("/enroll/{customerId}")
    public String enrollCustomer(@PathVariable Long customerId, final Model model) {
        RewardProgramResponse response = rewardService.enrollCustomerInRewardProgram(customerId);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(ApiResponse.success(response, "Cliente iscritto al programma rewards"));
        model.addAttribute("customer", response);
        model.addAttribute("inserted", true);
        return "rewardCustomer";
    }

    @GetMapping("/customer/{customerId}")
    public String getRewardProgram(@PathVariable Long customerId, final Model model) {
        RewardProgramResponse response = rewardService.getCustomerRewardProgram(customerId);
//        return ResponseEntity.ok(ApiResponse.success(response, "Programma rewards recuperato"));
        model.addAttribute("customer", response);
        return "rewardCustomer";
    }

//    @PostMapping("/{rewardProgramId}/redeem-points")
//    public String redeemPoints(	@PathVariable Long rewardProgramId, 
//    							@RequestParam BigDecimal amount, 
//    							@RequestParam(required = false) String description,
//    							final Model model) {
//        rewardService.redeemPoints(rewardProgramId, amount, description != null ? description : "Riscatto punti");
////        return ResponseEntity.ok(ApiResponse.success(null, "Punti riscattati con successo"));
//    }

//    @PostMapping("/{rewardProgramId}/check-tier")
//    public ResponseEntity<ApiResponse<Void>> checkAndUpdateTier(@PathVariable Long rewardProgramId) {
//        rewardService.checkAndUpdateTier(rewardProgramId);
//        return ResponseEntity.ok(ApiResponse.success(null, "Tier verificato e aggiornato"));
//    }
}
