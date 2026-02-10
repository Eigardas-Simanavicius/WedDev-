package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class bookStoreController {
  @GetMapping("/store")
  public String home() {
    return "store";
  }
}
