package it.gff.learn.structures.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@SpringBootApplication
public class GatewayServiceApplication {

	public static final String URL = "https://example.org";

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}



}
