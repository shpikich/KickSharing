package ru.yushkov.kicksharing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.entity.User;
import ru.yushkov.kicksharing.service.RentService;
import ru.yushkov.kicksharing.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final RentService rentService;

    public UserController(UserService userService, RentService rentService) {
        this.userService = userService;
        this.rentService = rentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{user_id}")
    public ResponseEntity<User> findUser(@PathVariable(value = "user_id") Long userId) {
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/last")
    public ResponseEntity<List<User>> findLastUsers() {
        return new ResponseEntity<>(userService.findLastUsers(), HttpStatus.OK);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<User> updateUserAge(@PathVariable(value = "user_id") Long userId, @RequestParam(value = "age") int age) {
        return new ResponseEntity<>(userService.changeUserAge(userId, age), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{user_id}/rent")
    public ResponseEntity<User> rentKickScooter(@PathVariable(value = "user_id") Long userId, @RequestBody List<KickScooter> kickScooters) {
        return new ResponseEntity<>(rentService.rentKickScooter(userId, kickScooters), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{user_id}/finish")
    public ResponseEntity<User> finishKickScooterRent(@PathVariable(value = "user_id") Long userId, @RequestBody List<KickScooter> kickScooters) {
        return new ResponseEntity<>(rentService.finishKickScooterRent(userId, kickScooters), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "user_id") Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<String>("User deleted", HttpStatus.OK);
    }
}
