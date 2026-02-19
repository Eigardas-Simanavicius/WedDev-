package com.example.demo.Services;

import com.example.demo.Entitys.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(){
        Product pro = new Product();
        pro.setName("Some book");
        pro.setPrice(23.44);
    }


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
