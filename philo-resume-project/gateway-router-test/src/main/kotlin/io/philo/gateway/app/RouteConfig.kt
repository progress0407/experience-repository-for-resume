package io.philo.gateway.app

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST

@Configuration
class RouteConfig {

    @Bean
    fun routes(builder: RouteLocatorBuilder,
               authFilter: AuthFilter): RouteLocator {

        return builder.routes()
            .route {
                it.path("/items/**")
                    .and().method(GET, POST)
                    .filters { f -> f.filter(authFilter) }
                    .uri("lb://ITEM-SERVICE")
            }
            .route {
                it.path("/orders/**")
                    .filters { it.removeRequestHeader("Cookie") }
                    .uri("lb://ORDER-SERVICE")
            }
            .route {
                it.path("/users/**")
                    .filters { it.removeRequestHeader("Cookie") }
                    .uri("lb://USER-SERVICE")
            }
            .build()
    }
}
