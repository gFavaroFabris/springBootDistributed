package it.gff.learn.structures.gatewayservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;
import java.util.Map;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.addRequestHeader;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.https;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@Configuration
public class BaseGateway {

    private static final Logger logger = LoggerFactory.getLogger(BaseGateway.class);

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {
        return route("get_route")
                .route(RequestPredicates.all(), http("http://localhost:8082/people"))
                .GET("", HandlerFunctions.no())
                .GET("/user/**", http())
                .filter(addRequestHeader("Hello", "World"))
                .build();
    }

    private static void logHeaderDebug(String key, List<String> value) {
        logger.info("head {} {}", key, value);
    }

}
