package it.spring.example.cashcard.financing;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per le rate di finanziamento
 */
public interface InstallmentRepository extends JpaRepository<Installment, Long> {
    
    List<Installment> findByFinancingPlanId(Long financingPlanId);
    
    Page<Installment> findByFinancingPlanId(Long financingPlanId, Pageable pageable);
    
    List<Installment> findByStatus(String status);
    
    long countByFinancingPlanIdAndStatus(Long financingPlanId, String status);
}
