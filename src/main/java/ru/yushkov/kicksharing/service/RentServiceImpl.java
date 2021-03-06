package ru.yushkov.kicksharing.service;

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
    private final static int maximumNumberOfScooters = 5;

    private final UserRepository userRepository;
    private final KickScooterRepository kickScooterRepository;

    public RentServiceImpl(UserRepository userRepository, KickScooterRepository kickScooterRepository) {
        this.userRepository = userRepository;
        this.kickScooterRepository = kickScooterRepository;
    }

    @Override
    public User rentKickScooter(Long userId, List<KickScooter> kickScooters) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            int userKickScooters = user.getKickScooters().size();
            if (kickScooters.size() + userKickScooters <= maximumNumberOfScooters) {
                if (user.getAge() >= minimumUserAge) {
                    List<KickScooter> listOfRentedKickScooters = new ArrayList<>(user.getKickScooters());
                    for (KickScooter requestedKickScooterName : kickScooters) {
                        List<KickScooter> allKickScooters = (List<KickScooter>) kickScooterRepository.findAll();
                        for (KickScooter kickScooter : allKickScooters) {
                            if (kickScooter.getName().equals(requestedKickScooterName.getName())) {
                                KickScooter rentedKickScooter = new KickScooter.Builder()
                                        .withName(kickScooter.getName())
                                        .withStatus(RENTED)
                                        .withId(kickScooter.getKickScooterId())
                                        .withUser(user)
                                        .build();
                                kickScooterRepository.save(rentedKickScooter);
                                listOfRentedKickScooters.add(rentedKickScooter);
                            }
                        }
                    }
                    if (userKickScooters != listOfRentedKickScooters.size()) {
                        User updatedUser = new User.Builder()
                                .withName(user.getName())
                                .withSurname(user.getSurname())
                                .withAge(user.getAge())
                                .withKickScooters(listOfRentedKickScooters)
                                .withId(user.getUserId())
                                .build();
                        userRepository.save(updatedUser);
                        return updatedUser;
                    } else throw new NoSuchElementException("KickScooters with this names wasn't found");
                }
                throw new IllegalArgumentException("User with id " + userId + " must be over 18 years old");
            }
            throw new IllegalArgumentException("User can't rent more than 5 kickScooters");
        }
        throw new NoSuchElementException("User with id " + userId + " wasn't found");
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
                            .withUser(optionalUser.get())
                            .build();
                    kickScooterRepository.save(availableKickScooter);
                } else {
                    throw new NoSuchElementException("KickScooter with id " + kickScooter.getKickScooterId() + " wasn't found");
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
            throw new NoSuchElementException("User with id " + userId + " wasn't found");
        }
    }
}
