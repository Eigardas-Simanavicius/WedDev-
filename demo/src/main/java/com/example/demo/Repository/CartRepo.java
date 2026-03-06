package com.example.demo.Repository;

import com.example.demo.Entitys.Cart;
import com.example.demo.Entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    @Query(nativeQuery=true, value="SELECT * FROM Cart WHERE FINISHED = true")
    public List<Cart> getAllByFinished();
}
