package ru.yushkov.kicksharing.service;

import ru.yushkov.kicksharing.entity.KickScooter;

import java.util.List;

public interface KickScooterService {
    List<KickScooter> addKickScooters(List<KickScooter> kickScooters);

    KickScooter deleteKickScooterById(Long kickScooterId);

    List<KickScooter> displayListOfAllScooters();

    List<KickScooter> displayListOfFreeKickScooters();
}
