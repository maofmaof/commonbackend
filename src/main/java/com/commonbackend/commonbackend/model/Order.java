package com.commonbackend.commonbackend.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

    public Order() {

    }

    public Order(String customerId, Date orderDate, Date requiredDate, Date shippedDate) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;
    private String customerId;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    public Set<OrderDetails> orderDetails;

}
