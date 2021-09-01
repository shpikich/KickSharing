package ru.yushkov.kicksharing.service;

import ru.yushkov.kicksharing.entity.User;

public interface UserService {
    User addUser(User user);

    User findUserById(Long id);

    User deleteUserById(Long id);

    User changeUserAge(Long id, int age);

}

