package com.example.stub.controller;

import com.example.stub.models.ErrorResponse;
import com.example.stub.models.User;
import db.connection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
@RequestMapping("/api")
public class MethodsController {


    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestBody User request) {
        if (request.getLogin() == null || request.getPassword() == null || request.getEmail() == null || request.getLogin().isEmpty() || request.getPassword().isEmpty() || request.getEmail().isEmpty()) {
            ErrorResponse response = new ErrorResponse("Bad request", 400);
            return ResponseEntity.status(400).body(response);
        } else {
            int rows = connection.insert(request);
            return ResponseEntity.status(201).body("Количесто добавленных строк = " + rows);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam String condition) {
        try {
            User user = connection.select(condition);
            return ResponseEntity.status(200).body(user);
        }catch (SQLException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
