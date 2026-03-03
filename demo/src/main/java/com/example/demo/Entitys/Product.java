package com.example.demo.Entitys;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "product_name",nullable = false,length = 100)
    private String name;

    private String description;

    private boolean Hidden = false;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts = new ArrayList<>();
    private Double price;
    public List<Cart> getCarts(){return carts;}
    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(Double newPrice){
        this.price = newPrice;
    }
    public void setDescription(String words){description = words;}
    public String getDescription(){return description;}
    public String getName(){return name;}
    public Double getPrice(){return price;}
    public Long getId(){return Id;}
    public void hideProduct(){
        Hidden = !Hidden;
    }
    public void unHideProduct(){Hidden = false;}
    public Boolean getHidden(){return Hidden;}


}
