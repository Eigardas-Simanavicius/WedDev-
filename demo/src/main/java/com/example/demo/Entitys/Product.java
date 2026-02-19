package com.example.demo.Entitys;

import jakarta.persistence.*;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "product_name",nullable = false,length = 100)
    private String name;

    private double price;

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(Double newPrice){
        this.price = newPrice;
    }
}
