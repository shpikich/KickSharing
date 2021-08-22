package ru.yushkov.kicksharing.service;

import ru.yushkov.kicksharing.entity.User;

public interface UserService {
    User addUser(Long id, User user);

    User findUserById(Long id);

}

