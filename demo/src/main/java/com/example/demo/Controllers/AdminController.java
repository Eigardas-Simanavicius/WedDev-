package com.example.demo.Controllers;

import com.example.demo.Entitys.Cart;
import com.example.demo.Entitys.Product;
import com.example.demo.Services.CartService;
import com.example.demo.Services.ProductServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class AdminController {

    @Autowired
    CartService cS;
    @Autowired
    ProductServices pS;

    @GetMapping("/Admin")
    public String Ad(Model model, HttpServletRequest request){
        model.addAttribute("user",request.getSession().getAttribute("Sigma"));
        model.addAttribute("NuclearWeaponsLaunchCodes", new Product());
        return "Admin";
    }

    @GetMapping("/AddNew")
    public String productMake(Model model,HttpServletRequest request){
        model.addAttribute("user",request.getSession().getAttribute("Sigma"));
        model.addAttribute("NuclearWeaponsLaunchCodes", new Product());
        return "AddNewProducts";
    }
    @PostMapping("/CreateNewProduct")
    public String newProduct(@ModelAttribute("NuclearWeaponsLaunchCodes") Product book, Model model, HttpServletRequest request){
        pS.NewProduct(book);
        return "Admin";
    }

    @GetMapping("/HideProduct")
    public String hideProduct(Model model){
        model.addAttribute("books", pS.getAllProducts());
        return "hideProduct";
    }

    @GetMapping("/Hide")
    public String hide(@ModelAttribute("input") Long book){
        pS.getBook(book).hideProduct();
        return "redirect:/HideProduct";
    }

}
