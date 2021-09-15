package ru.yushkov.kicksharing.service;

import ru.yushkov.kicksharing.entity.KickScooter;

import java.util.List;

public interface KickScooterService {
    List<KickScooter> addKickScooters(List<KickScooter> kickScooters);

    void deleteKickScooterById(Long kickScooterId);

    List<KickScooter> getListOfKickScooters(String status);
}
