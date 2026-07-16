package it.spring.example.cashcard.financing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per i piani di finanziamento
 */
public interface FinancingPlanRepository extends JpaRepository<FinancingPlan, Long> {
    
    List<FinancingPlan> findByCardId(Long cardId);
    
    List<FinancingPlan> findByCustomerId(Long customerId);
    
    List<FinancingPlan> findByStatus(String status);
    
    List<FinancingPlan> findByCardIdAndStatus(Long cardId, String status);
    
    List<FinancingPlan> findByCustomerIdAndStatus(Long customerId, String status);
}
