package it.spring.example.cashcard.service;

import it.spring.example.cashcard.dto.CardPaymentEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CashCardProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CashCardProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPayment(String cardId, String countId, Double amount) {
        kafkaTemplate.send("payment-topic", cardId, new CardPaymentEvent(cardId, countId, amount));
        System.out.println("Sent event for Order: " + cardId);
    }
}
