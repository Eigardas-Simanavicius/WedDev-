package com.example.demo.Repository;

import com.example.demo.Entitys.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    //new custom query - search by name
    @Query(nativeQuery=true, value="SELECT * FROM Users WHERE USER_NAME = ?1")
    public List<Users> getUsersByuserName(@Param ("name") String name);
}
