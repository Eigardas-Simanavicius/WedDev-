package com.example.demo.Controllers;

import com.example.demo.Entitys.Cart;
import com.example.demo.Entitys.Product;
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

            Users curr = uS.getUser((String) session.getAttribute("Sigma"));
            System.out.println(curr.getName() + " is here" + curr.getAdmin());
            model.addAttribute("user",curr);
            model.addAttribute("orders",curr.getOldOrders());
            if(curr.getAdmin() == true){
                return "redirect:/Admin";
            }
            return "Account";
        }
    }
    @GetMapping("/SignIn")
    public String SignIn(Model model){
        model.addAttribute("user", new Users());
        return "SignIn";
    }

    @PostMapping("/SignInProcess")
    public String formSubmit(@ModelAttribute("user") Users user, Model model, HttpServletRequest request){

        if(uS.getUser(user.getName()) != null && user.getPass() != null) {
                if (user.getPass().equals(uS.getUser(user.getName()).getPass())) {
                    Users loggedIn = uS.getUser(user.getName());
                    if(loggedIn.getCurrentCartId() == null){
                        loggedIn.setCart(cS.createNewCart());
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("Sigma", loggedIn.getName());
                    session.setAttribute("cart",loggedIn.getCurrentCartId().getId());
                    session.setAttribute("Logged in",true);
                    System.out.println("lOGIND IN ");
                    return "result2";
                }
        }
        return "SignIn";
    }

    @GetMapping("/logOut")
    public String logOut(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("Logged in",null);
        session.setAttribute("Sigma",null);
        return "redirect:/SignIn";
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
