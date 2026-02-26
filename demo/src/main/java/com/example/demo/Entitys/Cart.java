package com.example.demo.Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    List<Long> productIds = new ArrayList<>();
    public List<Long> getProductIds(){
        return productIds;
    }
    public void addProductId(Long Id){
        productIds.add(Id);
    }

    public Long getId(){return Id;}
}
