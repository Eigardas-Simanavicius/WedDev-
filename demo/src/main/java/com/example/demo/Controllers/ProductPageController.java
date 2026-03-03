package com.example.demo.Controllers;

import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.Users;
import com.example.demo.Services.ProductServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ProductPageController {

    @Autowired
    ProductServices pS;
    @GetMapping("/productPage")
    public String productPage(@ModelAttribute("input") Long book, Model model, HttpServletRequest request){
        model.addAttribute("book",pS.getBook(book));
        return "Product";
    }

}
