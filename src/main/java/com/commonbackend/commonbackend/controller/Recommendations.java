package com.commonbackend.commonbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.commonbackend.commonbackend.model.Recommendation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/recommendations")
public class Recommendations {
    
   
    private final WebClient webClient;

    @Autowired
    RestTemplate restTemplate;
    
    public Recommendations(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://recommendation-service/api/v1").build();
    } 

    @GetMapping
    public ResponseEntity<String> getAllRecommendations() {
       // return webClient.get().uri("/recommendations").retrieve().bodyToFlux(Recommendation.class);
       ResponseEntity<String> response = restTemplate.exchange("http://recommendation-service/api/v1/recommendations",
       HttpMethod.GET, null, new ParameterizedTypeReference<String>() {

       });

       return response;
  
    }

    @PostMapping
    public Recommendation addRecommendation(@RequestBody Recommendation recommendation) {

        webClient.post().uri("/recommendation").body(Mono.just(recommendation), Recommendation.class).retrieve()
                .bodyToMono(Recommendation.class);
        
        return recommendation;
    }

}