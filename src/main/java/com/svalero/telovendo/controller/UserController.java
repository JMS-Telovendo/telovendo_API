package com.svalero.telovendo.controller;

import com.svalero.telovendo.domain.User;
import com.svalero.telovendo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Lista todos los usuarios
    @GetMapping("/users")
    public List<User> getUsers() {

        List<User> users;
        users = userService.findAllUsers();
        return users;
    }
}
