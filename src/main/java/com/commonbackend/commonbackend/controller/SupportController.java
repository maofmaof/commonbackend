package com.commonbackend.commonbackend.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.commonbackend.commonbackend.model.SupportIssue;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SupportController {

    public SupportController(WebClient.Builder webClientBuilder) {
          this.webClient = webClientBuilder.baseUrl("http://customer-support/api/v1").build();
    }	
    private final WebClient webClient;

    @GetMapping("/supportissues/{customerId}")
    public Flux<SupportIssue> getSupportIssueTicketsById(@PathVariable int customerId) {

        return webClient.get().uri("/tasks/" + customerId).retrieve().bodyToFlux(SupportIssue.class);
    }

    @GetMapping("/supportissues")
    public Flux<SupportIssue> getAllSupportIssueTickets() {
        return webClient.get().uri("/tasks").retrieve().bodyToFlux(SupportIssue.class);
    }

    @PostMapping("/supportissue/{customerId}")
    public Mono<SupportIssue> postSupportIssue(@PathVariable int customerId, @RequestBody SupportIssue supportIssue) {

        supportIssue.setCustomerId(customerId);

        Mono<SupportIssue> issueResponse = webClient.post().uri("/task")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(supportIssue), SupportIssue.class)
                .retrieve().bodyToMono(SupportIssue.class);

        return issueResponse;
    }

}
