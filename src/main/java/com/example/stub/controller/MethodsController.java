package com.example.stub.controller;

import com.example.stub.models.ErrorResponse;
import com.example.stub.models.User;
import db.connection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MethodsController {


    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestBody User request) {
        String login = request.getLogin();
        String password = request.getPassword();
        String email = request.getEmail();
        if (login == null || password == null || email == null || login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            ErrorResponse response = new ErrorResponse("Bad request", 400);
            return ResponseEntity.status(400).body(response);
        } else {
            User user = new User(login, password, email);
            int rows = connection.insert(user);
            return ResponseEntity.status(201).body("Количесто добавленных строк = " + rows);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam String condition) {
        User user = connection.select(condition);
        if (user == null){
            ErrorResponse response = new ErrorResponse("No such login", 400);
            return ResponseEntity.status(500).body(response);
        }else {
            return ResponseEntity.status(200).body(user);
        }
    }
}
