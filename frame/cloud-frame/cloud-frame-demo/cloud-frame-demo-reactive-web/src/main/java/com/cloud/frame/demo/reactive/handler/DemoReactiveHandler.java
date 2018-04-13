package com.cloud.frame.demo.reactive.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Created by wd on 2018/4/10.
 */
@Component
public class DemoReactiveHandler {

    public Mono<ServerResponse> findAll(ServerRequest request) {
//        Flux<Restaurant> result = restaurantRepository.findAll();
//        return ok().contentType(APPLICATION_JSON_UTF8).body(result, Restaurant.class);
        return null;
    }

    public Mono<ServerResponse> findAllDelay(ServerRequest request) {
//        Flux<Restaurant> result = restaurantRepository.findAll().delayElements(Duration.ofSeconds(1));
//        return ok().contentType(APPLICATION_JSON_UTF8).body(result, Restaurant.class);
        return ok().contentType(APPLICATION_JSON_UTF8).body(Flux.empty(), Object.class);
    }

    public Mono<ServerResponse> get(ServerRequest request) {
//        String id = request.pathVariable("id");
//        Mono<Restaurant> result = restaurantRepository.findById(id);
//        return ok().contentType(APPLICATION_JSON_UTF8).body(result, Restaurant.class);
        return null;
    }

    public Mono<ServerResponse> create(ServerRequest request) {
//        Flux<Restaurant> restaurants = request.bodyToFlux(Restaurant.class);
//        Flux<Restaurant> result = restaurants
//                .buffer(10000)
//                .flatMap(rs -> reactiveMongoTemplate.insert(rs, Restaurant.class));
//        return ok().contentType(APPLICATION_JSON_UTF8).body(result, Restaurant.class);
        return null;
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
//        String id = request.pathVariable("id");
//        Mono<Void> result = restaurantRepository.deleteById(id);
//        return ok().contentType(APPLICATION_JSON_UTF8).build(result);
        return null;
    }
}
