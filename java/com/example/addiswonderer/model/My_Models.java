package com.example.addiswonderer.model;

public class My_Models {

    String name,email,password,comfirmpassword;

    public My_Models(String name, String email, String password, String comfirmpassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.comfirmpassword = comfirmpassword;
    }

    public My_Models() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComfirmpassword() {
        return comfirmpassword;
    }

    public void setComfirmpassword(String comfirmpassword) {
        this.comfirmpassword = comfirmpassword;
    }
}
