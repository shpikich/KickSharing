package ru.yushkov.kicksharing.service;

import ru.yushkov.kicksharing.entity.User;

import java.util.Optional;

public interface UserService {
    User addUser(User user);

    Optional<User> findUserById(Long id);

}

