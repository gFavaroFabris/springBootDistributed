package it.spring.example.cashcard.cards.limit;

import it.spring.example.cashcard.cards.limit.dto.CardLimitCreateRequest;
import it.spring.example.cashcard.cards.limit.dto.CardLimitResponse;
import it.spring.example.cashcard.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Servizio per la gestione dei limiti carte
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CardLimitService {

    private final CardLimitRepository cardLimitRepository;
    private final ModelMapper modelMapper;

    /**
     * Crea i limiti per una carta
     */
    public CardLimitResponse createCardLimit(CardLimitCreateRequest request) {
        if (cardLimitRepository.findByCardId(request.getCardId()).isPresent()) {
        	//TODO
//            throw new BusinessException("Limiti già definiti per questa carta", "LIMITS_ALREADY_SET");
        }

        CardLimit limit = CardLimit.builder()
                .cardId(request.getCardId())
                .dailyLimit(request.getDailyLimit())
                .dailySpent(BigDecimal.ZERO)
                .monthlyLimit(request.getMonthlyLimit())
                .monthlySpent(BigDecimal.ZERO)
                .singleTransactionLimit(request.getSingleTransactionLimit())
                .withdrawalEnabled(request.getWithdrawalEnabled() != null ? request.getWithdrawalEnabled() : true)
                .internationalEnabled(request.getInternationalEnabled() != null ? request.getInternationalEnabled() : true)
                .contactlessEnabled(request.getContactlessEnabled() != null ? request.getContactlessEnabled() : true)
                .build();

        CardLimit saved = cardLimitRepository.save(limit);
        return modelMapper.map(saved, CardLimitResponse.class);
    }

    /**
     * Recupera i limiti di una carta
     */
    public CardLimitResponse getCardLimit(Long cardId) {
        CardLimit limit = cardLimitRepository.findByCardId(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Limiti carta", "cardId", cardId));
        return modelMapper.map(limit, CardLimitResponse.class);
    }

    /**
     * Aggiorna i limiti di una carta
     */
    public CardLimitResponse updateCardLimit(Long cardId, CardLimitCreateRequest request) {
        CardLimit limit = cardLimitRepository.findByCardId(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Limiti carta", "cardId", cardId));

        limit.setDailyLimit(request.getDailyLimit());
        limit.setMonthlyLimit(request.getMonthlyLimit());
        limit.setSingleTransactionLimit(request.getSingleTransactionLimit());
        if (request.getWithdrawalEnabled() != null) limit.setWithdrawalEnabled(request.getWithdrawalEnabled());
        if (request.getInternationalEnabled() != null) limit.setInternationalEnabled(request.getInternationalEnabled());
        if (request.getContactlessEnabled() != null) limit.setContactlessEnabled(request.getContactlessEnabled());

        CardLimit updated = cardLimitRepository.save(limit);
        return modelMapper.map(updated, CardLimitResponse.class);
    }

    /**
     * Verifica se una transazione rispetta i limiti
     */
    public boolean canProcessTransaction(Long cardId, BigDecimal amount) throws ResourceNotFoundException {
        CardLimit limit = cardLimitRepository.findByCardId(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Limiti carta", "cardId", cardId));

        return amount.compareTo(limit.getDailyLimit()) <= 0 &&
               amount.compareTo(limit.getMonthlyLimit()) <= 0 &&
               amount.compareTo(limit.getSingleTransactionLimit()) <= 0;
    }

    /**
     * Aggiorna la spesa giornaliera
     */
    public void updateDailySpent(Long cardId, BigDecimal amount) {
        CardLimit limit = cardLimitRepository.findByCardId(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Limiti carta", "cardId", cardId));

        limit.setDailySpent(limit.getDailySpent().add(amount));
        cardLimitRepository.save(limit);
    }

    /**
     * Aggiorna la spesa mensile
     */
    public void updateMonthlySpent(Long cardId, BigDecimal amount) {
        CardLimit limit = cardLimitRepository.findByCardId(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Limiti carta", "cardId", cardId));

        limit.setMonthlySpent(limit.getMonthlySpent().add(amount));
        cardLimitRepository.save(limit);
    }

    /**
     * Resetta la spesa giornaliera
     */
    public void resetDailySpent(Long cardId) {
        cardLimitRepository.findByCardId(cardId).ifPresent(limit -> {
            limit.setDailySpent(BigDecimal.ZERO);
            cardLimitRepository.save(limit);
        });
    }

    /**
     * Resetta la spesa mensile
     */
    public void resetMonthlySpent(Long cardId) {
        cardLimitRepository.findByCardId(cardId).ifPresent(limit -> {
            limit.setMonthlySpent(BigDecimal.ZERO);
            cardLimitRepository.save(limit);
        });
    }
}
