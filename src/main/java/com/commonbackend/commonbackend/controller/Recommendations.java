package com.commonbackend.commonbackend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.commonbackend.commonbackend.model.Recommendation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class Recommendations {

    public Recommendations(WebClient.Builder webClientBuilder) {
       this.webClient = webClientBuilder.baseUrl("http://recommendation-service/api/v1").build();        
    }

    private final WebClient webClient;

    @GetMapping("/recommendations")
    public Flux<Recommendation> getAllRecommendations() {

        return webClient.get().uri("/recommendations").retrieve().bodyToFlux(Recommendation.class);     
    }

    @PostMapping("/recommendation")
    public Mono<Recommendation> addRecommendation(@RequestBody Recommendation recommendation) {

        Mono<Recommendation> recResponse = webClient.post()
                .uri("/recommendation").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(recommendation), Recommendation.class).retrieve()
                .bodyToMono(Recommendation.class);

        return recResponse;
    }

}