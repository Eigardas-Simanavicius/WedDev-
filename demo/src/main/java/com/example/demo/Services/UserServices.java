package com.example.demo.Services;

import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.Users;
import com.example.demo.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServices {
    @Autowired
    private UserRepository uR;


    public Users isValidUser(String name){
        List<Users> Result = uR.getUsersByuserName(name);
        return Result.getFirst();
    }

    public void addUser(){
        Users pro = new Users("Greg");
        pro.setName("Greg");
        uR.save(pro);
    }
}
