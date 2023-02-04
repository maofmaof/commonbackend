package com.commonbackend.commonbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="order details") 
public class OrderDetails {

    @EmbeddedId
    OrderDetailsPK id;
   
    private double unitPrice;
    private int quantity;
    private double Discount; // Skippar discount

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("orderId")
    @JoinColumn(name = "OrderId")
    Order order;

    @JsonIgnore
    @ManyToOne()
    @MapsId("productId")
    @JoinColumn(name = "ProductId")
    Product product;

    public OrderDetails() {
    }

    public OrderDetails(OrderDetailsPK id) {
        this.id = id;
    }

    public OrderDetails(double unitPrice, int quantity, double Discount) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.Discount = Discount;
    }

    public void setOrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
        this.id = new OrderDetailsPK(order.getOrderId(), product.getProductId());
    }

    public OrderDetails(double unitPrice, int quantity) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

}
