package com.example.demo.Repository;

import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(nativeQuery=true, value="SELECT * FROM Product WHERE HIDDEN = false")
    public List<Product> getUsersByProductByHidden();
}
