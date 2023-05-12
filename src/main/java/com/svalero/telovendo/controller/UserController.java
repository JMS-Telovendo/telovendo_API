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

    // Busca un usuario por ID
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id) {

        User user = userService.findUser(id);


        return user;
    }

    // Borra un usuario por id
    @DeleteMapping("/user/{id}")
    public User deleteUser(@PathVariable long id) {

        User user = userService.deleteUser(id);


        return user;
    }

    // Registra un nuevo usuario
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {

        User newUser = userService.addUser(user);


        return newUser;
    }

    // Modifica un usuario por id
    @PutMapping("/user/{id}")
    public User modifyUser(@RequestBody User user, @PathVariable long id) {

        User newUser = userService.modifyUser(user, id);

        return newUser;
    }
}
