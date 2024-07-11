package com.example.stub.controller;

import com.example.stub.models.ErrorResponse;
import com.example.stub.models.User;
import db.connection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;


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
            try(FileWriter writer = new FileWriter("files/output.csv", true)){
                writer.write("\n" + user.getLogin() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getDate());
            }catch (IOException e){
                System.out.println("No such file");
            }
            return ResponseEntity.status(200).body(user);
        }catch (SQLException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @GetMapping("/random")
    public ResponseEntity<?> random() {
        try(BufferedReader reader = new BufferedReader(new FileReader("files/random.txt"))){
            Random random = new Random();
            int randomLineNumber = random.nextInt(10) + 1;
            String line = null;
            for (int i = 1; i <= randomLineNumber; i++) {
                line = reader.readLine();
            }
            return ResponseEntity.status(200).body(line);
        }catch (IOException e){
            return ResponseEntity.status(500).body("No such file");
        }
    }
}
