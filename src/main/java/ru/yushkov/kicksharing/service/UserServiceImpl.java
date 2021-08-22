package ru.yushkov.kicksharing.service;

import org.springframework.stereotype.Service;
import ru.yushkov.kicksharing.entity.User;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    private final HashMap<Long, User> users;

    private UserServiceImpl() {
        this.users = new HashMap<Long, User>();
    }

    @Override
    public User addUser(Long id, User user) {
        user.setId(id);
        users.put(id, user);
        return user;
    }

    @Override
    public User findUserById(Long id) {
        return users.get(id);
    }

}
