package com.example.demo.Controllers;

import com.example.demo.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

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
}
