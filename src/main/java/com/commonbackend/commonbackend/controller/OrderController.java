package com.commonbackend.commonbackend.controller;

import com.commonbackend.commonbackend.model.Order;
import com.commonbackend.commonbackend.model.Price;
import com.commonbackend.commonbackend.service.OrderService;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    private final WebClient webClient;

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://foreign-currency/api/v1").build();
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/deleteOrder/{orderId}")
    public Optional<Order> deleteOrderById(@PathVariable int orderId) {
        // 10248
        return orderService.deleteById(orderId);
    }

    @GetMapping("/addOrder/{customerId}/{productId}") // ALFKI customerId
    public ResponseEntity<String> addProduct(@PathVariable String customerId, @PathVariable int productId) {

        boolean result = orderService.addOrder(customerId, productId);
        if (result) {
            return new ResponseEntity<String>("Order created", HttpStatus.CREATED);
        }
        logger.info("Validation error from client, product doesn't exist");
        return new ResponseEntity<String>("Order not created", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/convertCurrency/{currency}")
    public Mono<String> getOtherCurrencyPriceFromCart(@PathVariable String currency) {

        Price price = new Price("500", currency);

        return webClient.post().uri("/price").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(price), Price.class)
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class);

    }

}
