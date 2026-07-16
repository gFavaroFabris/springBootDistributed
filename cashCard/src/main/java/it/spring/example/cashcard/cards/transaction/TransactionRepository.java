package it.spring.example.cashcard.cards.transaction;

import it.spring.example.cashcard.shared.enums.TransactionStatus;
import it.spring.example.cashcard.shared.enums.TransactionType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository per la gestione delle transazioni
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    Page<Transaction> findByCardId(Long cardId, Pageable pageable);
    
    Page<Transaction> findByCustomerId(Long customerId, Pageable pageable);
    
    Page<Transaction> findByCardIdAndStatus(Long cardId, TransactionStatus status, Pageable pageable);
    
    Page<Transaction> findByCardIdAndType(Long cardId, TransactionType type, Pageable pageable);
    
    Optional<Transaction> findByReferenceNumber(String referenceNumber);
    
    @Query("SELECT t FROM Transaction t WHERE t.cardId = :cardId AND t.transactionDate BETWEEN :startDate AND :endDate")
    List<Transaction> findByCardIdAndDateRange(Long cardId, LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT t FROM Transaction t WHERE t.customerId = :customerId AND t.transactionDate BETWEEN :startDate AND :endDate")
    List<Transaction> findByCustomerIdAndDateRange(Long customerId, LocalDateTime startDate, LocalDateTime endDate);
    
    List<Transaction> findByStatus(TransactionStatus status);
    
    long countByCardIdAndStatus(Long cardId, TransactionStatus status);
}
