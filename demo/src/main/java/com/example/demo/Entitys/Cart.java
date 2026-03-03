package com.example.demo.Entitys;

import jakarta.persistence.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Product> products = new ArrayList<>();
    // This is going to be a silly implementation, but, I am tired, maybe fix later
    // Future Eigardas, This should be a map with products mapping quantities.
    List<Integer> quantities = new ArrayList<>();
    @ManyToMany(mappedBy = "oldOrder")
    private List<Users> users = new ArrayList<>();
    public boolean Finished = false;
    public double getPrice(){
        double currFinalPrice = 0.0;
        for (int i = 0; i < products.size(); i++) {
            currFinalPrice += products.get(i).getPrice() * quantities.get(i);
        }
        return currFinalPrice;
    }
    public List<Product> getProductIds(){
        return products;
    }
    public void addProductId(Product book){
        for (Product product : products) {
            System.out.println(product.getName());
            System.out.println(Id + " is the carts id we are booking to");
        }
        System.out.println(Id + " is the carts id we are booking to");
      if(!products.contains(book)){
          for (Product product : products) {
              System.out.println(product.getName());
          }
          products.add(book);
          book.getCarts().add(this);
          quantities.add(1);
      }else{
          increaseQuanitiy(book);
        }

        for (Product product : products) {
            System.out.println(product.getName());
        }

    }

    public void increaseQuanitiy(Product book){
        int n = products.indexOf(book);
        quantities.set(n,quantities.get(n)+1);
    }

    public void decreaseQuanitiy(Product book){
        int n = products.indexOf(book);
        if(quantities.get(n) <= 1){
            quantities.remove(n);
            removeProduct(book);
        }else {
            quantities.set(n, quantities.get(n) - 1);
        }
    }
    public Integer getQuantity(Integer n){
        return quantities.get(n);
    }
    public void removeProduct(Product book){
        int n = products.indexOf(book);
        quantities.remove(n);
        products.remove(book);
        book.getCarts().remove(this);

    }
    public List<Integer> getQuantities(){
        return quantities;
    }
    public Long getId(){return Id;}
}
