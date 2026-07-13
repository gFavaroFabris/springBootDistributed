package it.spring.example.cashcard.controller;

import it.spring.example.cashcard.dto.CardPaymentEvent;
import it.spring.example.cashcard.dto.CashCardResponse;
import it.spring.example.cashcard.service.CashCardProducer;
import it.spring.example.cashcard.service.CashCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    private final CashCardProducer cashCardProducer;

    public CashCardController(CashCardProducer cashCardProducer) {
        this.cashCardProducer = cashCardService;
    }

    @PostMapping("/{requestedId}")
    public ResponseEntity<CashCardResponse> findById(@PathVariable long cardId,
                                                     @ResponseBody CardPaymentEvent cashCardProducer) {
        return cashCardProducer.(cashCardProducer);
    }
}
