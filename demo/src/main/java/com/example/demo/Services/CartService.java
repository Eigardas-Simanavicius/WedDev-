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

    public void addProductToCart(Product book, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("Sigma is here");
        System.out.println(session.getAttribute("cart") + " is the cart");
        uS.getUser((String) session.getAttribute("Sigma")).getCurrentCartId().addProductId(book);
        for (Product product : (findCart((Long) session.getAttribute("cart"))).getProductIds()) {
            System.out.println(product.getName() + " is one of the books");
        }
    }

    public void removeFromCart(Product book, HttpServletRequest request) {
        HttpSession session = request.getSession();
        uS.getUser((String) session.getAttribute("Sigma")).getCurrentCartId().removeProduct(book);

    }


    public Cart createNewCart() {
        Cart cart = new Cart();
        cR.save(cart);
        return cart;
    }

    public Cart getCart(Users user) {
        return user.getCurrentCartId();
    }

    public Cart findCart(Long id) {
        return cR.findById(id).orElseThrow();
    }

    public Cart Cart(Users user) {
        Cart Id = user.getCurrentCartId();
        if (Id != null) {
            return Id;
        } else {
            Cart newCart = createNewCart();
            user.setCart(newCart);
            return user.getCurrentCartId();
        }
    }

    public void quantityUp(Product book, Cart cart) {
        cart.increaseQuanitiy(book);
    }

    public void quantityDown(Product book, Cart cart) {
        cart.decreaseQuanitiy(book);
    }

    public void checkOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Users curr = uS.getUser((String) session.getAttribute("Sigma"));
        Cart currCart = curr.getCurrentCartId();
        curr.finishOrder(currCart);
        curr.setCart(createNewCart());
        currCart.Finished = true;
        session.setAttribute("cart", curr.getCurrentCartId().getId());
        //uS.getUser("Admin").finishOrder(currCart);

    }

    public Cart getCartById(Long Id) {
        return cR.findById(Id).orElseThrow();
    }
}
