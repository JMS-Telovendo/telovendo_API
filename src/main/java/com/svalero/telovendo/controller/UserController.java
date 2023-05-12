package com.svalero.telovendo.controller;

import com.svalero.telovendo.domain.User;
import com.svalero.telovendo.exception.ErrorMessage;
import com.svalero.telovendo.exception.UserNotFoundException;
import com.svalero.telovendo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException unfe){
        logger.error(unfe.getMessage(),unfe);
        ErrorMessage notfound = new ErrorMessage(404,unfe.getMessage());
        return new ResponseEntity(notfound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> badRequestException(MethodArgumentNotValidException manve){

        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldname = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldname, message);
        });

        logger.error(manve.getMessage(),manve);
        ErrorMessage badRequest = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error(e.getMessage(),e);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
