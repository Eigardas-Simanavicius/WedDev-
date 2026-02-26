package com.example.demo.Controllers;

import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.Users;
import com.example.demo.Repository.CartRepo;
import com.example.demo.Services.CartService;
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

  @Autowired
  CartService cS;
  @GetMapping("/store")
  public String home(Model model) {
    Ps.addProduct();
    model.addAttribute("books", Ps.getAllProducts());
    return "store";
  }

  @GetMapping("/AddtoCart")
  public String AddtoCart(@ModelAttribute("input") Long book, Model model, HttpServletRequest request){

    cS.addProductToCart(Ps.getBook((book)), request);
    System.out.println(book);
    return "redirect:/store";

  }
}
