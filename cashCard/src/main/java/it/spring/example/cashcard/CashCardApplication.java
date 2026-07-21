package it.spring.example.cashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.Modulithic;

@Modulithic
@SpringBootApplication
public class CashCardApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(CashCardApplication.class, args);
    }

}
