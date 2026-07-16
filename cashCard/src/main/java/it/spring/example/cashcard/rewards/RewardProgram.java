package it.spring.example.cashcard.rewards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entità Programma di Rewards per il cliente
 */
@Entity
@Table(name = "reward_programs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class RewardProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal currentPoints = BigDecimal.ZERO;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal currentCashback = BigDecimal.ZERO;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal lifetimeCashback = BigDecimal.ZERO;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal lifetimePoints = BigDecimal.ZERO;

    @Column(nullable = false)
    @Builder.Default
    private Integer tier = 1;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime enrollmentDate;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column
    private LocalDate tierUpgradeDate;
}
