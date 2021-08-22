package ru.yushkov.kicksharing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yushkov.kicksharing.entity.User;
import ru.yushkov.kicksharing.service.UserServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object create(@Valid @RequestBody User user) {
        return userServiceImpl.addUser(12345L, user);
    }

    @GetMapping("/{user_id}")
    public Object findById(@PathVariable(value = "user_id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userServiceImpl.findUserById(id));
    }
}
