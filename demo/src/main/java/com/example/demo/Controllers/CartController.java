package com.example.demo.Controllers;

import com.example.demo.Entitys.Cart;
import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.Users;
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
        if(session.getAttribute("Logged in") != null){

            if(session.getAttribute("cart") == null) {
                session.setAttribute("cart",(uS.getUser((String) session.getAttribute("Sigma")).getCurrentCartId()));
            }
            model.addAttribute("user",session.getAttribute("Sigma"));
           model.addAttribute("cart", cS.findCart((Long) session.getAttribute("cart")));

            return "Cart";
        }else{
            return "redirect:/SignIn";
        }
    }

    @GetMapping("/RemovefromCart")
    public String RemovefromCart(@ModelAttribute("input") Long book, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();

        model.addAttribute("cart", cS.findCart((Long) session.getAttribute("cart")));
        model.addAttribute("quant", cS.findCart((Long) session.getAttribute("cart")).getQuantities());
        cS.removeFromCart(pS.getBook((book)),request);
        return "Cart";
    }

    @GetMapping("/Checkout")
    public String Checkout(Model model, HttpServletRequest request){
        cS.checkOut(request);
        return "redirect:/Cart";
    }

    @GetMapping("/Decreasequantitiy")
    public String decrease(@ModelAttribute("input") Long book,HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        cS.quantityDown(pS.getBook(book),cS.findCart((Long) session.getAttribute("cart")));
        return "redirect:/Cart";
    }

    @GetMapping("/Increasequantitiy")
    public String Increase(@ModelAttribute("input") Long book,HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        cS.quantityUp(pS.getBook(book),cS.findCart((Long) session.getAttribute("cart")));
        return "redirect:/Cart";
    }

    @GetMapping("/PrevOrders")
    public String PrevOrders(@ModelAttribute("input") String cart,HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Cart curr = cS.getCartById(Long.parseLong(cart));
        if(session.getAttribute("Logged in") != null){
            Users currUser = uS.getUser((String) session.getAttribute("Sigma"));
            model.addAttribute("user",currUser);
            model.addAttribute("cart",curr);
            session.setAttribute("currCart",curr.getId());
            System.out.println(curr.getStats() + " On click");
            if(currUser.getAdmin() == true){
                return "PrevOrderAdmin";
            }else {

                return "PrevOrders";
            }
        }else{
            return "redirect:/SignIn";
        }
    }
}
