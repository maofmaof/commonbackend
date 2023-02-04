package com.commonbackend.commonbackend.service;

import com.commonbackend.commonbackend.repository.OrderRepository;
import com.commonbackend.commonbackend.repository.ProductRepository;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.commonbackend.commonbackend.model.Order;
import com.commonbackend.commonbackend.model.OrderDetails;
import com.commonbackend.commonbackend.model.Product;

@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ProductRepository productRepository;

  public List<Order> getAllOrders() {

    return orderRepository.findAll();
  }

  public Optional<Order> deleteById(int id) {

    Optional<Order> order = orderRepository.findById(id);
    orderRepository.deleteById(id);
    return order;

  }

  public Boolean addOrder(String customerId, int productId) {

    if (productRepository.existsById(productId) == false) {
      return false;
    }

    var date = new Date();

    Order order = new Order(customerId, date, date, date);

    Product product = productRepository.findById(productId).get();

    product.setUnitsInStock(product.getUnitsInStock() - 1);
    product.setUnitsOnorder(product.getUnitsOnorder() + 1);
    productRepository.save(product);

    Set<OrderDetails> orderDetailsSet = new LinkedHashSet<OrderDetails>();

    OrderDetails orderDetails = new OrderDetails(product.getUnitPrice(), 5); // Quantity från client

    orderDetails.setOrderProduct(order, product);

    orderDetailsSet.add(orderDetails);

    order.setOrderDetails(orderDetailsSet);

    orderRepository.save(order);

    return true;
  }

}
