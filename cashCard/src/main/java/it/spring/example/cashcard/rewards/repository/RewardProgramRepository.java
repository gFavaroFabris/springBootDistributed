package it.spring.example.cashcard.rewards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.spring.example.cashcard.rewards.RewardProgram;

import java.util.Optional;

/**
 * Repository per i programmi di reward
 */
public interface RewardProgramRepository extends JpaRepository<RewardProgram, Long> {
    
    Optional<RewardProgram> findByCustomerId(Long customerId);
    
    long countByTier(Integer tier);
}
