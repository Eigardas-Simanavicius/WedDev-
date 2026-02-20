package com.example.demo.Controllers;

import com.example.demo.Entitys.Users;
import com.example.demo.vehicle;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @GetMapping("/Account")
    public void Account(){

    }
    @GetMapping("/SignIn")
    public String SignIn(Model model){
        model.addAttribute("user", new Users());
        return "SignIn";
    }

    @PostMapping("/SignIn")
    public String formSubmit(@ModelAttribute Users user,Model model){
        model.addAttribute("user", user);
        return "result2";
    }

}
