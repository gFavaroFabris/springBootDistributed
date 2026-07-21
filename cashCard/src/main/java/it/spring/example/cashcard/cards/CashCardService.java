package it.spring.example.cashcard.cards;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spring.example.cashcard.cards.dto.CashCardResponse;

/**
 * Servizio per la gestione delle carte bancarie
 */
@Service
@Transactional(readOnly = true)
public class CashCardService {

    private final CashCardRepository cardRepository;
//    private final CustomerService customerService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;
    
    public CashCardService(CashCardRepository cardRepository, ModelMapper modelMapper,
			ApplicationEventPublisher eventPublisher) {
		this.cardRepository = cardRepository;
//		this.customerService = customerService;
		this.modelMapper = modelMapper;
		this.eventPublisher = eventPublisher;
	}

	public Page<CashCard> getPageableCards(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }
	
    /**
     * Recupera una carta per ID
     * @throws NotFoundException 
     */
    public CashCardResponse getCardById(Long cardId) throws NotFoundException {
        return cardRepository.findCardResponseById(cardId)
        		.orElseThrow(NotFoundException::new);
    }

//    /**
//     * Crea una nuova carta
//     */
//    public CardResponse createCard(CardCreateRequest request) {
//        // Validazione: cliente deve esistere
//        if (!customerService.existsCustomer(request.getCustomerId())) {
//            throw new ResourceNotFoundException("Cliente", "id", request.getCustomerId());
//        }
//
//        // Validazione: numero carta unico
//        if (cardRepository.findByCardNumber(request.getCardNumber()).isPresent()) {
//            throw new BusinessException("Numero carta già registrato", "DUPLICATE_CARD_NUMBER");
//        }
//
//        // Validazione: data scadenza
//        if (request.getExpiryDate().isBefore(LocalDate.now())) {
//            throw new BusinessException("La carta è già scaduta", "CARD_ALREADY_EXPIRED");
//        }
//
//        String cardToken = generateCardToken();
//
//        CashCard card = CashCard.builder()
//                .customerId(request.getCustomerId())
//                .cardNumber(request.getCardNumber())
//                .cardToken(cardToken)
//                .cardholderName(request.getCardholderName())
//                .expiryDate(request.getExpiryDate())
//                .cvv(request.getCvv())
//                .issuer(request.getIssuer())
//                .cardType(request.getCardType())
//                .status(CardStatus.ACTIVE)
//                .dailyLimit(request.getDailyLimit())
//                .monthlyLimit(request.getMonthlyLimit())
//                .issuanceDate(request.getIssuanceDate())
//                .build();
//
//        CashCard saved = cardRepository.save(card);
//        
//        // Pubblica l'evento
//        eventPublisher.publishEvent(new CardCreatedEvent(
//                saved.getId(),
//                saved.getCustomerId(),
//                saved.getCardholderName(),
//                LocalDateTime.now()
//        ));
//        
//        return modelMapper.map(saved, CardResponse.class);
//    }

//    /**
//     * Recupera tutte le carte di un cliente
//     */
//    public List<CardResponse> getCardsByCustomer(Long customerId) {
//        if (!customerService.existsCustomer(customerId)) {
//            throw new ResourceNotFoundException("Cliente", "id", customerId);
//        }
//        return cardRepository.findByCustomerId(customerId).stream()
//                .map(card -> modelMapper.map(card, CardResponse.class))
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Recupera tutte le carte attive di un cliente
//     */
//    public List<CardResponse> getActiveCardsByCustomer(Long customerId) {
//        return cardRepository.findByCustomerIdAndStatus(customerId, CardStatus.ACTIVE).stream()
//                .map(card -> modelMapper.map(card, CardResponse.class))
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Blocca una carta
//     */
//    public CardResponse blockCard(Long cardId) {
//        CashCard card = cardRepository.findById(cardId)
//                .orElseThrow(() -> new ResourceNotFoundException("Carta", "id", cardId));
//
//        if (card.getStatus() == CardStatus.BLOCKED) {
//            throw new BusinessException("La carta è già bloccata", "CARD_ALREADY_BLOCKED");
//        }
//
//        card.setStatus(CardStatus.BLOCKED);
//        CashCard updated = cardRepository.save(card);
//        return modelMapper.map(updated, CardResponse.class);
//    }
//
//    /**
//     * Attiva una carta
//     */
//    public CardResponse activateCard(Long cardId) {
//        CashCard card = cardRepository.findById(cardId)
//                .orElseThrow(() -> new ResourceNotFoundException("Carta", "id", cardId));
//
//        card.setStatus(CardStatus.ACTIVE);
//        CashCard updated = cardRepository.save(card);
//        return modelMapper.map(updated, CardResponse.class);
//    }
//
//    /**
//     * Annulla una carta
//     */
//    public void cancelCard(Long cardId) {
//        CashCard card = cardRepository.findById(cardId)
//                .orElseThrow(() -> new ResourceNotFoundException("Carta", "id", cardId));
//        card.setStatus(CardStatus.CANCELLED);
//        cardRepository.save(card);
//    }
//
//    /**
//     * Genera un token univoco per la carta
//     */
//    private String generateCardToken() {
//        return "TOKEN_" + UUID.randomUUID().toString().replace("-", "").substring(0, 20).toUpperCase();
//    }
//
//    /**
//     * Genera un numero carta mascherato (ultimi 4 cifre visibili)
//     */
//    public String maskCardNumber(String cardNumber) {
//        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
//    }

}
