package com.commonbackend.commonbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Table(name="customers")
@Entity
public class Customer {

    Customer() {
        
    }
    
    @Id
    String customerId;
    
    String companyName;
    String contactName;
    String ContactTitle;
    String address;
    String city;
    String region;
    String postalCode;
    String country;
    String phone;
    String fax;
    
}
