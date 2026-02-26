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
        private Long CurrentCartId;


        public Users() {

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


        public Long getCurrentCartId(){
                return CurrentCartId;
        }

        public void setCartId(Long Id){
                CurrentCartId = Id;
        }
}
