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



    public Users getUser(String name){
        List<Users> Result = uR.getUsersByuserName(name);
        return Result.getFirst();
    }

    public List<Users> getUsers(){
        return uR.getUsers();

    }

    public void addUser(Users user){
        uR.save(user);
    }

    public void createAdmin(){
        Users Admin = new Users("Admin","Admin");
        Admin.setAdmin(true);
        addUser(Admin);
    }
    public void greatPurge(){
        uR.deleteAll();
    }

}
