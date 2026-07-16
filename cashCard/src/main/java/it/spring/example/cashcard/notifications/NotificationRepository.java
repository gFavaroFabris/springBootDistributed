package it.spring.example.cashcard.notifications;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per le notifiche
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    Page<Notification> findByCustomerId(Long customerId, Pageable pageable);
    
    Page<Notification> findByCustomerIdAndRead(Long customerId, Boolean read, Pageable pageable);
    
    List<Notification> findByCustomerIdAndReadFalse(Long customerId);
    
    long countByCustomerIdAndReadFalse(Long customerId);
}
