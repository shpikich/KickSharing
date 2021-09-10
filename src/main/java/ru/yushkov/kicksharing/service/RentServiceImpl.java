package ru.yushkov.kicksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.entity.User;
import ru.yushkov.kicksharing.repository.KickScooterRepository;
import ru.yushkov.kicksharing.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ru.yushkov.kicksharing.entity.Status.AVAILABLE;
import static ru.yushkov.kicksharing.entity.Status.RENTED;

@Service
public class RentServiceImpl implements RentService {

    private final static int minimumUserAge = 18;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KickScooterRepository kickScooterRepository;

    @Override
    public User rentKickScooter(Long userId, List<KickScooter> kickScooters) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            if (optionalUser.get().getAge() >= minimumUserAge) {
                List<KickScooter> listOfRentedKickScooters = new ArrayList<>(optionalUser.get().getKickScooters());
                for (KickScooter requestedKickScooterNames : kickScooters) {
                    List<KickScooter> allKickScooters = (List<KickScooter>) kickScooterRepository.findAll();
                    for (KickScooter kickScooter : allKickScooters) {
                        if (kickScooter.getName().equals(requestedKickScooterNames.getName())) {
                            KickScooter rentedKickScooter = new KickScooter.Builder()
                                    .withName(kickScooter.getName())
                                    .withStatus(RENTED)
                                    .withId(kickScooter.getKickScooterId())
                                    .build();
                            kickScooterRepository.save(rentedKickScooter);
                            listOfRentedKickScooters.add(rentedKickScooter);
                        }
                    }
                }
                User user = optionalUser.get();
                if (user.getKickScooters().size() != listOfRentedKickScooters.size()) {
                    User updatedUser = new User.Builder()
                            .withName(user.getName())
                            .withSurname(user.getSurname())
                            .withAge(user.getAge())
                            .withKickScooters(listOfRentedKickScooters)
                            .withId(user.getUserId())
                            .build();
                    userRepository.save(updatedUser);
                    return updatedUser;
                } else throw new NoSuchElementException("KickScooter with this name wasn't found");
            }
            throw new RuntimeException("User must be over 18 years old");
        }
        throw new NoSuchElementException("User with this id wasn't found");
    }

    @Override
    public User finishKickScooterRent(Long userId, List<KickScooter> kickScooters) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            for (KickScooter kickScooter : kickScooters) {
                Optional<KickScooter> optionalKickScooter = kickScooterRepository.findById(kickScooter.getKickScooterId());
                if (optionalKickScooter.isPresent()) {
                    KickScooter availableKickScooter = new KickScooter.Builder()
                            .withName(kickScooter.getName())
                            .withStatus(AVAILABLE)
                            .withId(kickScooter.getKickScooterId())
                            .build();
                    kickScooterRepository.save(availableKickScooter);
                } else {
                    throw new NoSuchElementException("KickScooter with this id wasn't found");
                }
            }
            List<KickScooter> listOfRentedKickScooters = optionalUser.get().getKickScooters();
            listOfRentedKickScooters.removeIf(kickScooter -> kickScooter.getStatus() == AVAILABLE);

            User user = optionalUser.get();
            User updatedUser = new User.Builder()
                    .withName(user.getName())
                    .withSurname(user.getSurname())
                    .withAge(user.getAge())
                    .withKickScooters(listOfRentedKickScooters)
                    .withId(user.getUserId())
                    .build();
            userRepository.save(updatedUser);
            return updatedUser;
        } else {
            throw new NoSuchElementException("User with this id wasn't found");
        }
    }
}
