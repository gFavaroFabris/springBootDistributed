package it.spring.example.cashcard.cards;

import it.spring.example.cashcard.cards.dto.CashCardResponse;
import it.spring.example.cashcard.shared.enums.CardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repository per la gestione delle carte bancarie
 */
public interface CashCardRepository extends JpaRepository<CashCard, Long> {

	Optional<CashCardResponse> findCardResponseById(Long cardId);
//    Optional<CashCard> findByCardNumber(String cardNumber);
//    Optional<CashCard> findByCardToken(String cardToken);
//    List<CashCard> findByCustomerId(Long customerId);
//    List<CashCard> findByStatus(CardStatus status);
//    List<CashCard> findByCustomerIdAndStatus(Long customerId, CardStatus status);
}
