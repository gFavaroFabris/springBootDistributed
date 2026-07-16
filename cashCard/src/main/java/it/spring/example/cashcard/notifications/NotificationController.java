package it.spring.example.cashcard.notifications;

import it.spring.example.cashcard.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione delle notifiche
 */
@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/customer/{customerId}/unread")
    public ResponseEntity<ApiResponse<List<Notification>>> getUnreadNotifications(@PathVariable Long customerId) {
        List<Notification> notifications = notificationService.getUnreadNotifications(customerId);
        return ResponseEntity.ok(ApiResponse.success(notifications, "Notifiche non lette"));
    }

    @GetMapping("/customer/{customerId}/unread-count")
    public ResponseEntity<ApiResponse<Long>> getUnreadCount(@PathVariable Long customerId) {
        long count = notificationService.getUnreadCount(customerId);
        return ResponseEntity.ok(ApiResponse.success(count, "Conteggio notifiche non lette"));
    }

    @PostMapping("/{notificationId}/read")
    public ResponseEntity<ApiResponse<Void>> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok(ApiResponse.success(null, "Notifica marcata come letta"));
    }

    @PostMapping("/customer/{customerId}/read-all")
    public ResponseEntity<ApiResponse<Void>> markAllAsRead(@PathVariable Long customerId) {
        notificationService.markAllAsRead(customerId);
        return ResponseEntity.ok(ApiResponse.success(null, "Tutte le notifiche marcate come lette"));
    }
}
