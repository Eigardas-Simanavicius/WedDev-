package com.example.demo.Entitys;

import jakarta.persistence.*;

@Entity
public class Users {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;

        @Column(name = "UserName",nullable = false,length = 100)
        private String name;

        private String Password;

        public void setName(String newName) {
            this.name = newName;
        }

        public void setPassword(String NewPass){
            this.Password = NewPass;
        }

        public String getName(){return name;}
        public String getPass(){return Password;}


}
