package com.example.stub.models;


import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String login;
    private String password;
    private String date;


    // Конструктор
    public User(String login, String password) {
        this.login = login;
        this.password = password;
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.date = formatter.format(currentDate);
    }


    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }


    public String getDate() {
        return date;
    }

}