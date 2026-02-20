package com.example.demo.Controllers;

import com.example.demo.Entitys.Users;
import com.example.demo.Services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    UserServices uS;

    @GetMapping("/Account")
    public String Account(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("Sigma") == null){
            System.out.println(session.getAttribute("user"));
            return "redirect:/SignIn";
        }
        return "Account";
    }
    @GetMapping("/SignIn")
    public String SignIn(Model model){
        model.addAttribute("user", new Users("Greg"));
        return "SignIn";
    }

    @PostMapping("/SignIn")
    public String formSubmit(@ModelAttribute("user") Users user, Model model, HttpServletRequest request){
        //model.addAttribute("user", uS.isValidUser(user.getName()));
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.setAttribute("Sigma","user");
        return "result2";

    }

    @GetMapping("/SignUp")
    public String newAccount(){
        uS.addUser();
        return "SignUp";
    }
}
