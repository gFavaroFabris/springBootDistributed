package it.spring.example.cashcard.cards.transaction;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/**
 * Servizio per la gestione delle transazioni
 */
@Service
@Transactional
public class TransactionService {

//    private final TransactionRepository transactionRepository;
//    private final CashCardService cardService;
//    private final ModelMapper modelMapper;
//    private final ApplicationEventPublisher eventPublisher;
//
//    public TransactionService(TransactionRepository transactionRepository, CashCardService cardService,
//			ModelMapper modelMapper, ApplicationEventPublisher eventPublisher) {
//		this.transactionRepository = transactionRepository;
//		this.cardService = cardService;
//		this.modelMapper = modelMapper;
//		this.eventPublisher = eventPublisher;
//	}
//
//	/**
//     * Crea una nuova transazione
//     */
//    public TransactionResponse createTransaction(TransactionCreateRequest request) {
//        // Validazione: la carta deve esistere
//        cardService.getCardById(request.getCardId());
//
//        // Calcolo dell'importo totale (amount + fee)
//        BigDecimal totalAmount = request.getAmount().add(request.getFee());
//
//        // Genera numero di riferimento univoco
//        String referenceNumber = generateReferenceNumber();
//
//        Transaction transaction = Transaction.builder()
//                .cardId(request.getCardId())
//                .customerId(request.getCustomerId())
//                .type(request.getType())
//                .status(TransactionStatus.COMPLETED)
//                .amount(request.getAmount())
//                .fee(request.getFee())
//                .totalAmount(totalAmount)
//                .description(request.getDescription())
//                .merchantName(request.getMerchantName())
//                .merchantCategory(request.getMerchantCategory())
//                .transactionDate(LocalDateTime.now())
//                .referenceNumber(referenceNumber)
//                .authorizationCode(generateAuthorizationCode())
//                .build();
//
//        Transaction saved = transactionRepository.save(transaction);
//        
//        // Pubblica l'evento
//        eventPublisher.publishEvent(new TransactionCompletedEvent(
//                saved.getId(),
//                saved.getCardId(),
//                saved.getCustomerId(),
//                saved.getDescription(),
//                saved.getAmount(),
//                LocalDateTime.now()
//        ));
//        
//        return modelMapper.map(saved, TransactionResponse.class);
//    }
//
//    /**
//     * Recupera una transazione per ID
//     */
//    public TransactionResponse getTransactionById(Long transactionId) {
//        Transaction transaction = transactionRepository.findById(transactionId)
//                .orElseThrow(() -> new ResourceNotFoundException("Transazione", "id", transactionId));
//        return modelMapper.map(transaction, TransactionResponse.class);
//    }
//
//    /**
//     * Recupera lo storico transazioni di una carta con paginazione
//     */
//    public Page<TransactionResponse> getCardTransactionHistory(Long cardId, Pageable pageable) {
//        cardService.getCardById(cardId); // Validazione
//        Page<Transaction> transactions = transactionRepository.findByCardId(cardId, pageable);
//        return transactions.map(t -> modelMapper.map(t, TransactionResponse.class));
//    }
//
//    /**
//     * Recupera lo storico transazioni di un cliente con paginazione
//     */
//    public Page<TransactionResponse> getCustomerTransactionHistory(Long customerId, Pageable pageable) {
//        Page<Transaction> transactions = transactionRepository.findByCustomerId(customerId, pageable);
//        return transactions.map(t -> modelMapper.map(t, TransactionResponse.class));
//    }
//
//    /**
//     * Recupera le transazioni filtrate per stato
//     */
//    public Page<TransactionResponse> getTransactionsByStatus(Long cardId, TransactionStatus status, Pageable pageable) {
//        cardService.getCardById(cardId);
//        Page<Transaction> transactions = transactionRepository.findByCardIdAndStatus(cardId, status, pageable);
//        return transactions.map(t -> modelMapper.map(t, TransactionResponse.class));
//    }
//
//    /**
//     * Recupera le transazioni filtrate per tipo
//     */
//    public Page<TransactionResponse> getTransactionsByType(Long cardId, TransactionType type, Pageable pageable) {
//        cardService.getCardById(cardId);
//        Page<Transaction> transactions = transactionRepository.findByCardIdAndType(cardId, type, pageable);
//        return transactions.map(t -> modelMapper.map(t, TransactionResponse.class));
//    }
//
//    /**
//     * Recupera transazioni per range di date
//     */
//    public List<TransactionResponse> getTransactionsByDateRange(Long cardId, LocalDateTime startDate, LocalDateTime endDate) {
//        cardService.getCardById(cardId);
//        List<Transaction> transactions = transactionRepository.findByCardIdAndDateRange(cardId, startDate, endDate);
//        return transactions.stream()
//                .map(t -> modelMapper.map(t, TransactionResponse.class))
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Storna una transazione
//     */
//    public TransactionResponse reverseTransaction(Long transactionId, String reason) {
//        Transaction transaction = transactionRepository.findById(transactionId)
//                .orElseThrow(() -> new ResourceNotFoundException("Transazione", "id", transactionId));
//
//        if (transaction.getStatus() == TransactionStatus.REVERSED) {
//            throw new BusinessException("La transazione è già stata stornata", "ALREADY_REVERSED");
//        }
//
//        if (transaction.getStatus() != TransactionStatus.COMPLETED) {
//            throw new BusinessException("Solo le transazioni completate possono essere stornate", "CANNOT_REVERSE");
//        }
//
//        transaction.setStatus(TransactionStatus.REVERSED);
//        transaction.setReversalReason(reason);
//
//        Transaction updated = transactionRepository.save(transaction);
//        return modelMapper.map(updated, TransactionResponse.class);
//    }
//
//    /**
//     * Conta le transazioni in sospeso per una carta
//     */
//    public long getPendingTransactionCount(Long cardId) {
//        cardService.getCardById(cardId);
//        return transactionRepository.countByCardIdAndStatus(cardId, TransactionStatus.PENDING);
//    }
//
//    /**
//     * Genera un numero di riferimento univoco
//     */
//    private String generateReferenceNumber() {
//        return "TRX" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
//    }
//
//    /**
//     * Genera un codice di autorizzazione
//     */
//    private String generateAuthorizationCode() {
//        return "AUTH" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
//    }
}
