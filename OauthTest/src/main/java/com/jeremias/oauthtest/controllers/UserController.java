package com.jeremias.oauthtest.controllers;

import com.jeremias.oauthtest.models.User;
import com.jeremias.oauthtest.models.UserDto;
import com.jeremias.oauthtest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/*
* ver como actualizar el usuario, sin la nacesidad de pasar todos los fields
*  */

@RestController
@RequestMapping(path = "api/user/", produces = "application/json")
public class UserController {

    @Autowired
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserDto userRequest) {
        userService.registerUser(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllStudents(), HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto userToUpdate) {
        return new ResponseEntity<>(userService.updateUser(id, userToUpdate), HttpStatus.OK);
    }

    @GetMapping(path = "findByEmail/{email}")
    public ResponseEntity<User> getUserById(@PathVariable String email) {
        return new ResponseEntity<>(userService.findStudentByEmail(email), HttpStatus.OK);
    }

    @GetMapping(path = "findById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findStudentById(id), HttpStatus.OK);
    }
}
