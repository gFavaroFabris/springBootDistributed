package it.spring.example.cashcard.cards.limit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository per i limiti delle carte
 */
public interface CardLimitRepository extends JpaRepository<CardLimit, Long> {
    Optional<CardLimit> findByCardId(Long cardId);
}
