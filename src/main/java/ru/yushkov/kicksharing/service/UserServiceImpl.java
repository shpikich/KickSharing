package ru.yushkov.kicksharing.service;

import org.springframework.stereotype.Service;
import ru.yushkov.kicksharing.entity.User;
import ru.yushkov.kicksharing.repository.UserRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final static int numberOfDisplayedUsers = 5;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new NoSuchElementException("User with id " + userId + " wasn't found");
    }

    @Override
    public User deleteUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            return optionalUser.get();
        }
        throw new NoSuchElementException("User with id " + userId + " wasn't found");
    }

    @Override
    public User changeUserAge(Long userId, int age) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            User updatedUser = new User.Builder()
                    .withName(user.getName())
                    .withSurname(user.getSurname())
                    .withAge(age)
                    .withId(user.getUserId())
                    .build();
            userRepository.save(updatedUser);
            return updatedUser;
        }
        throw new NoSuchElementException("User with id " + userId + " wasn't found");
    }

    @Override
    public List<User> findLastUsers() {
        List<User> lastUsers = new ArrayList<>();
        LinkedList<User> userList = new LinkedList<>((List<User>) userRepository.findAll());
        if (userList.size() != 0) {
            Long lastUserIndex = userList.getLast().getUserId();
            do {
                Optional<User> optionalUser = userRepository.findById(lastUserIndex);
                if (optionalUser.isPresent()) {
                    lastUsers.add(optionalUser.get());
                }
                lastUserIndex--;
            } while (lastUsers.size() != numberOfDisplayedUsers);
            return lastUsers;
        }
        throw new NoSuchElementException("There are no users in the database!");
    }
}
