package com.example.stub.controller;

import com.example.stub.models.ErrorResponse;
import com.example.stub.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MethodsController {
    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestBody User request) {
        String login = request.getLogin();
        String password = request.getPassword();

        if (login == null || password == null) {
            ErrorResponse response = new ErrorResponse("Bad request", 400);
            return ResponseEntity.status(400).body(response);
        } else {
            User response = new User(login, password);
            return ResponseEntity.status(201).body(response);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<User> get() {
        User response = new User("user", "1234");
        return ResponseEntity.status(200).body(response);
    }
}
