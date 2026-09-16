package it.gff.learn.structures.gatewayservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import java.util.List;

@RestController
public class BaseGateway {

    private static final Logger logger = LoggerFactory.getLogger(BaseGateway.class);
    
	@RequestMapping("/circuitbreakerfallback")
	public String circuitbreakerfallback() {
		return "This is a fallback";
	}

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        logger.info("Configuring gateway routes...");
        return builder.routes()
                // Route 1: Forward /people/** to http://localhost:8082
                .route("people_route", r -> r.path("/people/**")
                    .uri("http://localhost:8082"))
                
                // Route 2: Forward /user/**, apply the header, and route to http://localhost:8082
                .route("user_route", r -> r.path("/user/**")
                    .filters(f -> f.addResponseHeader("X-AnotherHeader", "baz"))
                    .uri("http://localhost:8082"))
                .build();
    }

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
		return http.httpBasic(Customizer.withDefaults())
				.csrf(csrf -> csrf.disable())
				.authorizeExchange(exchange -> exchange
						.pathMatchers("/anything/**").authenticated()
						.anyExchange().permitAll())
				.build();
	}

	@Bean
	public MapReactiveUserDetailsService reactiveUserDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
		return new MapReactiveUserDetailsService(user);
	}

}
