package it.spring.example.cashcard.notifications;

import it.spring.example.cashcard.shared.events.CardCreatedEvent;
import it.spring.example.cashcard.shared.events.CustomerRegisteredEvent;
import it.spring.example.cashcard.shared.events.TransactionCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Servizio per le notifiche con event listeners
 */
@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * Listener per evento TransactionCompletedEvent
     */
    @ApplicationModuleListener
    public void onTransactionCompleted(TransactionCompletedEvent event) {
        Notification notification = Notification.builder()
                .customerId(event.customerId())
                .type("TRANSACTION")
                .title("Transazione Completata")
                .message(String.format("Transazione di €%.2f completata: %s", 
                        event.amount(), event.description()))
                .description(String.format("Ref: TRX-%d", event.transactionId()))
                .relatedEntityId(event.transactionId())
                .read(false)
                .build();

        notificationRepository.save(notification);
    }

    /**
     * Listener per evento CardCreatedEvent
     */
    @ApplicationModuleListener
    public void onCardCreated(CardCreatedEvent event) {
        Notification notification = Notification.builder()
                .customerId(event.customerId())
                .type("CARD")
                .title("Nuova Carta Creata")
                .message(String.format("La tua nuova carta intestata a %s è stata creata con successo",
                        event.cardholderName()))
                .description(String.format("Ref: CARD-%d", event.cardId()))
                .relatedEntityId(event.cardId())
                .read(false)
                .build();

        notificationRepository.save(notification);
    }

    /**
     * Listener per evento CustomerRegisteredEvent
     */
    @ApplicationModuleListener
    public void onCustomerRegistered(CustomerRegisteredEvent event) {
        Notification notification = Notification.builder()
                .customerId(event.customerId())
                .type("SYSTEM")
                .title("Benvenuto!")
                .message(String.format("Ciao %s, benvenuto nel nostro sistema di gestione carte",
                        event.firstName()))
                .description("Registrazione completata con successo")
                .relatedEntityId(event.customerId())
                .read(false)
                .build();

        notificationRepository.save(notification);
    }

    /**
     * Recupera le notifiche di un cliente
     */
    public java.util.List<Notification> getUnreadNotifications(Long customerId) {
        return notificationRepository.findByCustomerIdAndReadFalse(customerId);
    }

    /**
     * Conteggio notifiche non lette
     */
    public long getUnreadCount(Long customerId) {
        return notificationRepository.countByCustomerIdAndReadFalse(customerId);
    }

    /**
     * Marca una notifica come letta
     */
    public void markAsRead(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setRead(true);
            notification.setReadAt(LocalDateTime.now());
            notificationRepository.save(notification);
        });
    }

    /**
     * Marca tutte le notifiche di un cliente come lette
     */
    public void markAllAsRead(Long customerId) {
        notificationRepository.findByCustomerIdAndReadFalse(customerId).forEach(notification -> {
            notification.setRead(true);
            notification.setReadAt(LocalDateTime.now());
            notificationRepository.save(notification);
        });
    }
}
