package com.example.demo.Services;

import com.example.demo.Entitys.Cart;
import com.example.demo.Entitys.Product;
import com.example.demo.Entitys.Users;
import com.example.demo.Repository.CartRepo;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepo cR;

    @Autowired
    UserServices uS;

    @Autowired
    ProductRepository pR;

    public void addProductToCart(Product book, HttpServletRequest request){
        HttpSession session = request.getSession();

        Cart cart = getCart(getCartId(uS.getUser((String) session.getAttribute("Sigma"))));

        System.out.println(cart.getId());
        cart.addProductId(book.getId());
        session.setAttribute("cart",cart.getId());
        System.out.println((session.getAttribute("cart")));

    };

    public Cart createNewCart()
    {
        Cart cart = new Cart();
        cR.save(cart);
        return cart;
    }

    public Cart getCart(Long Id){
        return cR.findById(Id).orElse(createNewCart());
    }

    public Long getCartId(Users user){
        Long Id =  user.getCurrentCartId();
        if (Id != null){
            return Id;
        }else{
            long newId = createNewCart().getId();
            user.setCartId(newId);
            return user.getCurrentCartId();
        }
    }
    public void attachCart(Users users, Long cartId){
        users.setCartId(cartId);
    }

}
