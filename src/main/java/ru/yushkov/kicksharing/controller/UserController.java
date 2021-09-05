package ru.yushkov.kicksharing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yushkov.kicksharing.entity.User;
import ru.yushkov.kicksharing.service.UserService;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{user_id}")
    public ResponseEntity<User> find(@PathVariable(value = "user_id") Long userId) {
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<User> update(@PathVariable(value = "user_id") Long userId, @RequestParam(value = "age") int age) {
        return new ResponseEntity<>(userService.changeUserAge(userId, age), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> delete(@PathVariable(value = "user_id") Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<String>("User deleted", HttpStatus.OK);
    }
}
