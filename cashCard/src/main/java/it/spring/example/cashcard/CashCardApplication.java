package it.spring.example.cashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulithic;

@Modulithic
@SpringBootApplication
public class CashCardApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(CashCardApplication.class, args);
    }
    
}
