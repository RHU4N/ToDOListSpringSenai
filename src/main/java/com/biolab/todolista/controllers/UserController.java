package com.biolab.todolista.controllers;

import com.biolab.todolista.DTOs.User.LoginReq;
import com.biolab.todolista.DTOs.User.UserReq;
import com.biolab.todolista.repositories.UserRepository;
import com.biolab.todolista.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody @Valid UserReq userReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userReq));
    }

    @GetMapping
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody @Valid UserReq userReq){
        return ResponseEntity.ok(userService.updateUser(id,userReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("Deletou");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReq loginReq){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginReq));
    }
}
