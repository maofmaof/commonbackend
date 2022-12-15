package com.commonbackend.commonbackend.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commonbackend.commonbackend.model.Product;

public interface ProductRepository extends JpaRepository <Product , Integer> {

    void save(Optional<Product> productToUpdate);

   
    
}
