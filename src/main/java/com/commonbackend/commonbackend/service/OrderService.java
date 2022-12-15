package com.commonbackend.commonbackend.service;

import com.commonbackend.commonbackend.repository.OrderRepository;
import com.commonbackend.commonbackend.repository.ProductRepository;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

  public Order addOrder(String customerId, int productId) {

    var date = new Date();
    Order order = new Order(customerId, date, date, date);
    order = orderRepository.save(order);

    Product product = productRepository.findById(productId).get();
    product.setUnitsInStock(product.getUnitsInStock() - 1);
    product.setUnitsOnorder(product.getUnitsOnorder() + 1);
    productRepository.save(product);

    Set<OrderDetails> orderDetailsSet = new LinkedHashSet<OrderDetails>();

    OrderDetails orderDetails = new OrderDetails(product.getUnitPrice(), product.getQuantityPerUnit());
    orderDetails.setOrderProduct(order, product);
    orderDetailsSet.add(orderDetails);
    
    order.setOrderDetails(orderDetailsSet);


    return orderRepository.findById(order.getOrderId()).get();
  }

}
