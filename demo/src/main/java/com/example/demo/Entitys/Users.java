package com.example.demo.Entitys;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Users {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer Id;

        @Column(name = "USER_NAME",nullable = false,length = 100)
        private String name;

        private String Password;
        @OneToOne
        private Cart cart;
        private Boolean isAdmin = false;
        @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
        private List<Cart> oldOrder = new ArrayList<>();

        public Users() {

        }
        public void finishOrder(Cart cart){
            System.out.println("why are we here?");
            if(!oldOrder.contains(cart)) {
                oldOrder.add(cart);
            }

        }
        public List<Cart> getOldOrders(){
            return oldOrder;
        }
        public void setName(String newName) {
            this.name = newName;
        }

        public void setPass(String NewPass){
            this.Password = NewPass;
        }

        public String getName(){return name;}
        public String getPass(){return Password;}

        public Users(String name,String password){
                this.name = name;
                this.Password = password;
        }


        public Cart getCurrentCartId(){
                return cart;
        }

        public void setCart(Cart cart){
                this.cart = cart;
        }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }


}
