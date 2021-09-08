package ru.yushkov.kicksharing.service;

import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.entity.User;

import java.util.List;

public interface RentService {
    User rentKickScooter(Long userId, List<KickScooter> kickScooters);

    User finishKickScooterRent(Long userId, List<KickScooter> kickScooters);
}
