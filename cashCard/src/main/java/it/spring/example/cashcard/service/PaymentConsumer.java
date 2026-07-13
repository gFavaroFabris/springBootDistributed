package it.spring.example.cashcard.service;

import it.spring.example.cashcard.dto.CardPaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);

    private static final String PAYMENT_CONSUMER = """
                 Received event from Kafka:
                 cardId %s
                 countId %s
                 amount %.2f
                """;

    @KafkaListener(topics = "payment-topic", groupId = "my-cqrs-group")
    public void consume(CardPaymentEvent event) {
        log.info(PAYMENT_CONSUMER.formatted(event.cardId(), event.countId(), event.amount()));
    }

}
