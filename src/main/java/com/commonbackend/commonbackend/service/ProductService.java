package com.commonbackend.commonbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.commonbackend.commonbackend.model.Product;
import com.commonbackend.commonbackend.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    ProductRepository productRepository;

    public List<Product> getAllProducts() {
         
        return productRepository.findAll();

    }

}
