package com.commonbackend.commonbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.commonbackend.commonbackend.model.SupportIssue;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class SupportController {

   

    @Autowired
    RestTemplate restTemplate;

    private final WebClient webClient;

    public SupportController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://recommendation-service/api/v1").build();
    } 

    

    @GetMapping("/supportissues/{customerId}")
    public Flux<SupportIssue> getSupportIssueTicketsById(@PathVariable int customerId) {

        return webClient.get().uri("/tasks/" + customerId).retrieve().bodyToFlux(SupportIssue.class);
    }
    
    @GetMapping("/supportissues")
    public Flux<SupportIssue> getAllSupportIssueTickets() {
        return webClient.get().uri("/tasks").retrieve().bodyToFlux(SupportIssue.class);
    }

    @PostMapping("supportissue/{customerId}")
    public Mono<SupportIssue> postSupportIssue(@PathVariable int customerId, @RequestBody SupportIssue supportIssue) {

        supportIssue.setCustomerId(customerId);

        return webClient.post().uri("/task").body(Mono.just(supportIssue), SupportIssue.class)
                .retrieve().bodyToMono(SupportIssue.class);

    }

}
