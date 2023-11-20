package com.tempotalent.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;
    @Value("${microservice.address.recruiter}")
    private String recruiterAddress;
    @Value("${microservice.address.job}")
    private String jobAddress;
    @Value("${microservice.address.candidate}")
    private String candidateAddress;
    @Value("${microservice.address.frontEnd}")
    private String frontEndAddress;
    

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                
                .route(r -> r
                        .path("/api/tempo-talent-recruiter/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .removeRequestHeader("Cookie"))
                        .uri(recruiterAddress))

                .route(r -> r
                        .path("/api/tempo-talent-job/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .removeRequestHeader("Cookie"))
                        .uri(jobAddress))
                .route(r -> r
                        .path("/api/tempo-talent-candidate/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .removeRequestHeader("Cookie"))
                        .uri(candidateAddress))
                
                .route(r -> r
                        .path("/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .removeRequestHeader("Cookie"))
                        .uri(frontEndAddress))
                .build();
    }

}
