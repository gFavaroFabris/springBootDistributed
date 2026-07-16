package it.spring.example.cashcard.rewards.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.spring.example.cashcard.rewards.RewardMovement;

import java.util.List;

/**
 * Repository per i movimenti di reward
 */
public interface RewardMovementRepository extends JpaRepository<RewardMovement, Long> {
    
    Page<RewardMovement> findByRewardProgramId(Long rewardProgramId, Pageable pageable);
    
    List<RewardMovement> findByMovementType(String movementType);
    
    long countByRewardProgramId(Long rewardProgramId);
}
