package com.example.demo.Entitys;

import jakarta.persistence.*;

@Entity
public class Users {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer Id;

        @Column(name = "USER_NAME",nullable = false,length = 100)
        private String name;

        private String Password;

        public Users() {

        }

        public void setName(String newName) {
            this.name = newName;
        }

        public void setPassword(String NewPass){
            this.Password = NewPass;
        }

        public String getName(){return name;}
        public String getPass(){return Password;}

        public Users(String name){
                this.name = name;
                this.Password = "0000";
        }


        public Users(String name,String password){
                this.name = name;
                this.Password = password;
        }

}
