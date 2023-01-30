package com.commonbackend.commonbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.commonbackend.commonbackend.model.Recommendation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/recommendations")
public class Recommendations {

    private final WebClient webClient;

    public Recommendations(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083/api/v1").build();
    }

    @GetMapping
    public Flux<Recommendation> getAllRecommendations() {
        return webClient.get().uri("/recommendations").retrieve().bodyToFlux(Recommendation.class);
    }

    @PostMapping
    public Recommendation addRecommendation(@RequestBody Recommendation recommendation) {

        webClient.post().uri("/recommendation").body(Mono.just(recommendation), Recommendation.class).retrieve()
                .bodyToMono(Recommendation.class);
        return recommendation;
    }

}