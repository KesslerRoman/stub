package com.example.stub.models;


import java.sql.Date;

public class User {
    private String login;
    private String email;
    private String password;
    private Date date;


    // Конструктор
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        java.util.Date utilDate = new java.util.Date();
        this.date = new Date(utilDate.getTime());
    }


    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public Date getDate() {
        return date;
    }

}