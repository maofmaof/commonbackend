package com.commonbackend.commonbackend.controller;

import com.commonbackend.commonbackend.model.Order;
import com.commonbackend.commonbackend.model.Price;
import com.commonbackend.commonbackend.service.OrderService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    private final WebClient webClient;

    public OrderController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083/api/v1").build();
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/deleteOrder/{orderId}") // Borde väl vara deletemapping?
    public Optional<Order> deleteOrderById(@PathVariable int orderId) {
        // 10248
        return orderService.deleteById(orderId);
    }

    @GetMapping("/addOrder/{customerId}/{productId}") // ALFKI customerId
    public ResponseEntity<Order> addProduct(@PathVariable String customerId, @PathVariable int productId) {
        if (productId > 77 || productId <= 0) { // göra denna dynamisk
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Order>(orderService.addOrder(customerId, productId), HttpStatus.CREATED);
    }

    @PostMapping("/convertCurrency/{currency}")
    public Mono<String> getOtherCurrencyPriceFromCart(@PathVariable String currency) {

        Price price = new Price("500", currency);
        // kortslutning, förstår inte "Provide the list of products as a request
        // object., finns inget cart objekt?"

        return webClient.post().uri("http://localhost:8082/api/v1/price").body(Mono.just(price), Price.class)
        .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class);

    }
    /*
     * POST /convertCurrency/{currency}
     * Provide the list of products as a request object.
     * 
     */

}
