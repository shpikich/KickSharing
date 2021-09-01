package ru.yushkov.kicksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yushkov.kicksharing.entity.User;
import ru.yushkov.kicksharing.repository.UserRepository;

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
    public User findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new NoSuchElementException("User with this id wasn't found");
    }

    @Override
    public User deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return optionalUser.get();
        }
        throw new NoSuchElementException("User with this id wasn't found");
    }

    @Override
    public User changeUserAge(Long id, int age) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            User updatedUser = new User.Builder()
                    .withName(user.getName())
                    .withSurname(user.getSurname())
                    .withAge(age)
                    .withId(user.getId())
                    .build();
            userRepository.save(updatedUser);
            return updatedUser;
        }
        throw new NoSuchElementException("User with this id wasn't found");
    }
}
