package it.gff.learn.structures.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayServiceApplication {

	public static final String URL = "https://example.org";

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}



}
