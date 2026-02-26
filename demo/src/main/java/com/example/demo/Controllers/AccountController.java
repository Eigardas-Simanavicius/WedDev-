package com.example.demo.Controllers;

import com.example.demo.Entitys.Cart;
import com.example.demo.Entitys.Users;
import com.example.demo.Services.CartService;
import com.example.demo.Services.ProductServices;
import com.example.demo.Services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    UserServices uS;

    @Autowired
    CartService cS;

    @Autowired
    ProductServices pS;

    @GetMapping("/Account")
    public String Account(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        if(session.getAttribute("Logged in") == null){
            System.out.println(session.getAttribute("user") + "is not logged in ");
            return "redirect:/SignIn";
        }else{
            model.addAttribute("user",session.getAttribute("Sigma"));
            uS.getUser(session.getAttribute("Sigma").toString());
            return "Account";
        }
    }
    @GetMapping("/SignIn")
    public String SignIn(Model model){
        List<Users> list = uS.getUsers();
        model.addAttribute("user", new Users());
        return "SignIn";
    }

    @PostMapping("/SignInProcess")
    public String formSubmit(@ModelAttribute("user") Users user, Model model, HttpServletRequest request){

        if(uS.getUser(user.getName()) != null) {
            if (user.getPass() != null) {
                if (user.getPass().equals(uS.getUser(user.getName()).getPass())) {
                    HttpSession session = request.getSession();
                    System.out.println(user.getName());
                    session.setAttribute("Sigma", user.getName());
                    session.setAttribute("Logged in",true);
                    System.out.println("lOGIND IN ");
                    return "result2";
                }
            }
        }
        return "SignIn";
    }

    @GetMapping("/SignUp")
    public String newAccount(Model model){
        model.addAttribute("user", new Users());
        return "SignUp";
    }

    @PostMapping("/SignUpProcess")
    public String newAccountMade(@ModelAttribute("user") Users user, Model model){
        uS.addUser(user);
        return "SignIn";
    }
}
