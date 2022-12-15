package com.commonbackend.commonbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "products")
public class Product {

  Product() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int productId;

  private String productName;
  private int supplierId;
  private int categoryId;
  private String quantityPerUnit;
  private double unitPrice;
  private int unitsInStock;
  private int unitsOnorder;
  private int reorderlevel;

  @Column(name = "Discontinued")
  private boolean isDiscontinued;

  public Product(int unitsOnOrder, int unitsInStock, double unitPrice, int quantity, double Discount) {
  
  }

}
/*
 * private int productID;
 * private String productName;
 * private int supplierID;
 * private int categoryID;
 * private String quantityPerUnit;
 * private double unitPrice;
 * private int unitsInStock;
 * private int unitsOnorder;
 * private int Reorderlevel;
 * private boolean isDiscontinued;
 */