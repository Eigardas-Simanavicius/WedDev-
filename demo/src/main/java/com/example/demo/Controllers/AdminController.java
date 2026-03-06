package com.example.demo.Controllers;

import com.example.demo.Entitys.Cart;
import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.Users;
import com.example.demo.Enums.OrderCartStatus;
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
@Controller
public class AdminController {

    @Autowired
    CartService cS;
    @Autowired
    ProductServices pS;
    @Autowired
    UserServices uS;

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

    @GetMapping("/ViewOrders")
    public String viewOrders(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        Users curr = uS.getUser((String) session.getAttribute("Sigma"));
        model.addAttribute("user",curr);
        model.addAttribute("orders",cS.getAllFinished());
        return "Account";

    }

    @GetMapping("/updateProduct")
    public String updateProductChoice(Model model){
        model.addAttribute("books", pS.getAllProducts());
        return "updateProduct";
    }

    @GetMapping("/updateProductDetails")
    public String updateProductDetails(@ModelAttribute("input") Long book,Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("toChange",book);
        model.addAttribute("newProduct", new Product());
        return "updateProductDetails";
    }

    @PostMapping("/updateProductDetails")
    public String updateProductDetailsPost(@ModelAttribute("newProduct") Product book,Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        pS.getBook((Long) session.getAttribute("toChange")).update(book);
        return "updateProduct";
    }

    @GetMapping("/changeStatus")
    public String changeStatus(@ModelAttribute("Status")OrderCartStatus status,HttpServletRequest request){
        System.out.println(request.getSession().getAttribute("currCart"));
        cS.getCartById((Long) request.getSession().getAttribute("currCart")).setStatus(status);
        return "redirect:/Account";
    }

}
