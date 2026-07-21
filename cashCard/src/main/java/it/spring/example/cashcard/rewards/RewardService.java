package it.spring.example.cashcard.rewards;

import it.spring.example.cashcard.customers.CustomerService;
import it.spring.example.cashcard.rewards.dto.RewardProgramResponse;
import it.spring.example.cashcard.rewards.repository.RewardMovementRepository;
import it.spring.example.cashcard.rewards.repository.RewardProgramRepository;
import it.spring.example.cashcard.shared.exception.BusinessException;
import it.spring.example.cashcard.shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Servizio per la gestione dei programmi di rewards
 */
@Service
@Transactional
public class RewardService {

    private final RewardProgramRepository rewardProgramRepository;
    private final RewardMovementRepository rewardMovementRepository;
    private final CustomerService customerService;
    private final ModelMapper modelMapper;
    
    public RewardService(RewardProgramRepository rewardProgramRepository,
			RewardMovementRepository rewardMovementRepository, CustomerService customerService,
			ModelMapper modelMapper) {
		this.rewardProgramRepository = rewardProgramRepository;
		this.rewardMovementRepository = rewardMovementRepository;
		this.customerService = customerService;
		this.modelMapper = modelMapper;
	}

	/**
     * Crea un nuovo programma di rewards per un cliente
     */
    public RewardProgramResponse enrollCustomerInRewardProgram(Long customerId) {
        // Validazione: cliente deve esistere
        customerService.getCustomerById(customerId);

        // Validazione: cliente non è già iscritto
        if (rewardProgramRepository.findByCustomerId(customerId).isPresent()) {
            throw new BusinessException("Cliente già iscritto al programma rewards", "ALREADY_ENROLLED");
        }

        RewardProgram program = RewardProgram.builder()
                .customerId(customerId)
                .currentPoints(BigDecimal.ZERO)
                .currentCashback(BigDecimal.ZERO)
                .lifetimePoints(BigDecimal.ZERO)
                .lifetimeCashback(BigDecimal.ZERO)
                .tier(1) // Bronze
                .active(true)
                .build();

        RewardProgram saved = rewardProgramRepository.save(program);
        return mapToResponse(saved);
    }

    /**
     * Recupera il programma di rewards di un cliente
     */
    public RewardProgramResponse getCustomerRewardProgram(Long customerId) {
        RewardProgram program = rewardProgramRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", customerId));
        return mapToResponse(program);
    }

    /**
     * Aggiungi punti al programma
     */
    public void addPoints(Long rewardProgramId, Long transactionId, BigDecimal amount, String description) {
        RewardProgram program = rewardProgramRepository.findById(rewardProgramId)
                .orElseThrow(() -> new ResourceNotFoundException("Programma rewards", "id", rewardProgramId));

        program.setCurrentPoints(program.getCurrentPoints().add(amount));
        program.setLifetimePoints(program.getLifetimePoints().add(amount));

        rewardProgramRepository.save(program);

        // Registra il movimento
        RewardMovement movement = RewardMovement.builder()
                .rewardProgramId(rewardProgramId)
                .transactionId(transactionId)
                .movementType("EARNED")
                .pointsEarned(amount)
                .cashbackEarned(BigDecimal.ZERO)
                .description(description)
                .transactionDate(java.time.LocalDateTime.now())
                .build();

        rewardMovementRepository.save(movement);
    }

    /**
     * Aggiungi cashback al programma
     */
    public void addCashback(Long rewardProgramId, Long transactionId, BigDecimal amount, String description) {
        RewardProgram program = rewardProgramRepository.findById(rewardProgramId)
                .orElseThrow(() -> new ResourceNotFoundException("Programma rewards", "id", rewardProgramId));

        program.setCurrentCashback(program.getCurrentCashback().add(amount));
        program.setLifetimeCashback(program.getLifetimeCashback().add(amount));

        rewardProgramRepository.save(program);

        // Registra il movimento
        RewardMovement movement = RewardMovement.builder()
                .rewardProgramId(rewardProgramId)
                .transactionId(transactionId)
                .movementType("EARNED")
                .pointsEarned(BigDecimal.ZERO)
                .cashbackEarned(amount)
                .description(description)
                .transactionDate(java.time.LocalDateTime.now())
                .build();

        rewardMovementRepository.save(movement);
    }

    /**
     * Riscatta punti
     */
    public void redeemPoints(Long rewardProgramId, BigDecimal amount, String description) {
        RewardProgram program = rewardProgramRepository.findById(rewardProgramId)
                .orElseThrow(() -> new ResourceNotFoundException("Programma rewards", "id", rewardProgramId));

        if (program.getCurrentPoints().compareTo(amount) < 0) {
            throw new BusinessException("Punti insufficienti per il riscatto", "INSUFFICIENT_POINTS");
        }

        program.setCurrentPoints(program.getCurrentPoints().subtract(amount));
        rewardProgramRepository.save(program);

        // Registra il movimento
        RewardMovement movement = RewardMovement.builder()
                .rewardProgramId(rewardProgramId)
                .transactionId(-1L) // No transaction
                .movementType("REDEEMED")
                .pointsEarned(amount.negate())
                .cashbackEarned(BigDecimal.ZERO)
                .description(description)
                .transactionDate(java.time.LocalDateTime.now())
                .build();

        rewardMovementRepository.save(movement);
    }

    /**
     * Verifica e aggiorna il tier del cliente basato sui punti acquisiti
     */
    public void checkAndUpdateTier(Long rewardProgramId) {
        RewardProgram program = rewardProgramRepository.findById(rewardProgramId)
                .orElseThrow(() -> new ResourceNotFoundException("Programma rewards", "id", rewardProgramId));

        int newTier = calculateTier(program.getLifetimePoints());

        if (newTier > program.getTier()) {
            program.setTier(newTier);
            program.setTierUpgradeDate(LocalDate.now());
            rewardProgramRepository.save(program);
        }
    }

    /**
     * Calcola il tier basato sui punti lifetime
     * Tier 1 (Bronze): 0-999 punti
     * Tier 2 (Silver): 1000-4999 punti
     * Tier 3 (Gold): 5000-9999 punti
     * Tier 4 (Platinum): 10000+ punti
     */
    private int calculateTier(BigDecimal lifetimePoints) {
        if (lifetimePoints.compareTo(new BigDecimal("10000")) >= 0) {
            return 4; // Platinum
        } else if (lifetimePoints.compareTo(new BigDecimal("5000")) >= 0) {
            return 3; // Gold
        } else if (lifetimePoints.compareTo(new BigDecimal("1000")) >= 0) {
            return 2; // Silver
        }
        return 1; // Bronze
    }

    /**
     * Mappa entità a DTO con il nome del tier
     */
    private RewardProgramResponse mapToResponse(RewardProgram program) {
        RewardProgramResponse response = modelMapper.map(program, RewardProgramResponse.class);
        response.setTierName(getTierName(program.getTier()));
        return response;
    }

    /**
     * Converte il numero del tier al nome
     */
    private String getTierName(Integer tier) {
        return switch (tier) {
            case 2 -> "Silver";
            case 3 -> "Gold";
            case 4 -> "Platinum";
            default -> "Bronze";
        };
    }
}
