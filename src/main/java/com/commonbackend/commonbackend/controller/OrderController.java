package com.commonbackend.commonbackend.controller;

import com.commonbackend.commonbackend.model.Order;
import com.commonbackend.commonbackend.service.OrderService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    OrderService orderService;

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
    public ResponseEntity<?> getOtherCurrencyPriceFromCart(@PathVariable String currency) {

        return ResponseEntity.ok().build();
    }

}
