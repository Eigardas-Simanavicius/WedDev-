package com.example.demo.Services;
import java.util.stream.Collectors;

import com.example.demo.Entitys.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(){
        Product pro = new Product();
        pro.setName("Some book");
        pro.setPrice(23.44);

        //productRepository.save(pro);
    }


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Long getBookId(Product book){
        return 12L;
    }

    public Product getBook(Long Id){
        System.out.println(productRepository.findById(Id).orElseThrow().getPrice());
        return productRepository.findById(Id).orElseThrow();
    }

    public void NewProduct(Product book){
        productRepository.save(book);
    }

    public List<Product> getAllShownProducts(){
        return productRepository.getUsersByProductByHidden();
    }
}
