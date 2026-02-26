package com.example.demo.Repository;

import com.example.demo.Entitys.Cart;
import com.example.demo.Entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
}
