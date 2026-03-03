package com.example.demo.Controllers;

import com.example.demo.Entitys.Cart;
import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.Users;
import com.example.demo.Repository.CartRepo;
import com.example.demo.Services.CartService;
import com.example.demo.Services.ProductServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    model.addAttribute("books", Ps.getAllShownProducts());
    return "store";
  }

  @GetMapping("/AddtoCart")
  public String AddtoCart(@ModelAttribute("input") Long book, Model model, HttpServletRequest request){

    HttpSession session = request.getSession();

    Cart cart = cS.findCart((Long) session.getAttribute("cart"));
    Product product = Ps.getBook(book);
      cS.addProductToCart(product,request);

    System.out.println(cart.getQuantities() + " is the quantities and there is also" + cart.getProductIds().size());
    model.addAttribute("books",Ps.getAllProducts());
    return "redirect:/store";

  }

}
