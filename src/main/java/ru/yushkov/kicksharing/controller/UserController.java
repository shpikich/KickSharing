package ru.yushkov.kicksharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yushkov.kicksharing.entity.User;
import ru.yushkov.kicksharing.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{user_id}")
    public Object findById(@PathVariable(value = "user_id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userService.findUserById(id));
    }
}
