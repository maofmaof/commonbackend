package com.commonbackend.commonbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commonbackend.commonbackend.model.Order;

public interface OrderRepository extends JpaRepository <Order, Integer> {
    
}
