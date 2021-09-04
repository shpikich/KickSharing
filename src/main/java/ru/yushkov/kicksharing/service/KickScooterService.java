package ru.yushkov.kicksharing.service;

import ru.yushkov.kicksharing.entity.KickScooter;

public interface KickScooterService {
    KickScooter addKickScooter(KickScooter kickScooter);

    KickScooter deleteKickScooterById(Long id);
}
