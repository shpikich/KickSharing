package ru.yushkov.kicksharing.service;

import ru.yushkov.kicksharing.entity.KickScooter;

import java.util.List;

public interface KickScooterService {
    void addKickScooters(List<KickScooter> kickScooters);

    void deleteKickScooterById(Long kickScooterId);

    List<KickScooter> displayListOfAllScooters();

    List<KickScooter> displayListOfFreeKickScooters();
}
