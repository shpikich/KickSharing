package ru.yushkov.kicksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.entity.User;
import ru.yushkov.kicksharing.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        throw new NoSuchElementException("User with this id wasn't found");
    }

    @Override
    public User deleteUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            return optionalUser.get();
        }
        throw new NoSuchElementException("User with this id wasn't found");
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
        throw new NoSuchElementException("User with this id wasn't found");
    }

    @Override
    public List<User> findLastFiveUsers() {
        List<User> lastFiveUsers = new ArrayList<>();
        List<User> userList = (List<User>) userRepository.findAll();
        for (int i = userList.size(); i >= userList.size() - 4; i--) {
            Optional<User> optionalUser = userRepository.findById((long) i);
            if (optionalUser.isPresent()) {
                lastFiveUsers.add(optionalUser.get());
            }
        }
        return lastFiveUsers;
    }
}
