package com.example.demo.Controllers;

import com.example.demo.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
  @Autowired
  ProductServices pS;
  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("books",pS.getAllProducts());
    return "homepage";
  }
}
