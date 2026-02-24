package com.example.demo.Controllers;

import com.example.demo.Entitys.Users;
import com.example.demo.Services.ProductServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class bookStoreController {
  @Autowired
  ProductServices Ps;
  @GetMapping("/store")
  public String home(Model model) {
    Ps.addProduct();
    model.addAttribute("books", Ps.getAllProducts());
    return "store";
  }

  @PostMapping("/AddtoCart")
  public String AddtoCart(@ModelAttribute("user") Users user, Model model, HttpServletRequest request){
    System.out.println("Added to Cart");
    return "store";

  }
}
