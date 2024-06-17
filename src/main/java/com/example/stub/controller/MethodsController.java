package com.example.stub.controller;

import com.example.stub.models.ErrorResponse;
import com.example.stub.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/api")
public class MethodsController {


    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestBody String payload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(payload);

        if (jsonNode.size() != 2) {
            ErrorResponse response = new ErrorResponse("Bad request", 400);
            return ResponseEntity.status(400).body(response);
        } else {
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();

            Map.Entry<String, JsonNode> firstField = fieldsIterator.next();
            String firstFieldName = firstField.getKey();

            Map.Entry<String, JsonNode> lastField = fieldsIterator.next();
            String lastFieldName = lastField.getKey();

            if (!Objects.equals(firstFieldName, "login") || !Objects.equals(lastFieldName, "password")) {
                ErrorResponse response = new ErrorResponse("Bad request", 400);
                return ResponseEntity.status(400).body(response);
            }

            String login = firstField.getValue().asText();
            String password = lastField.getValue().asText();
            User response = new User(login, password);
            return ResponseEntity.status(201).body(response);

        }

    }

    @GetMapping("/get")
    public ResponseEntity<User> get() {
        User response = new User("default_user", "default_password");
        return ResponseEntity.status(200).body(response);
    }
}
