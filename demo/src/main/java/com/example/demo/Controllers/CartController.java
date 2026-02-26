package com.example.demo.Controllers;

import com.example.demo.Services.CartService;
import com.example.demo.Services.ProductServices;
import com.example.demo.Services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class CartController {

    @Autowired
    UserServices uS;

    @Autowired
    ProductServices pS;

    @Autowired
    CartService cS;

    @GetMapping("/Cart")
    public String Cart(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        if(session.getAttribute("Logged in") == null){
            return "redirect:/SignIn";
        }else{
            model.addAttribute("user",session.getAttribute("Sigma"));
            uS.getUser(session.getAttribute("Sigma").toString());
            System.out.println(session.getAttribute("cart"));
            model.addAttribute("books",pS.getAllProductswithId(cS.getCart((Long) session.getAttribute("cart")).getProductIds()));
            return "Cart";
        }
    }
}
