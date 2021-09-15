package ru.yushkov.kicksharing.service;

import ru.yushkov.kicksharing.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User findUserById(Long userId);

    User deleteUserById(Long userId);

    User changeUserAge(Long userId, int age);

    List<User> findLastUsers();

}

