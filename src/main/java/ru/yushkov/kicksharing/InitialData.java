package ru.yushkov.kicksharing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.repository.KickScooterRepository;

@Component
public class InitialData implements CommandLineRunner {

    @Autowired
    private KickScooterRepository kickScooterRepository;

    @Override
    public void run(String... args) throws Exception {
        KickScooter[] kickScooters = {
                new KickScooter.Builder().withName("W001").build(),
                new KickScooter.Builder().withName("W002").build(),
                new KickScooter.Builder().withName("W003").build(),
                new KickScooter.Builder().withName("W004").build(),
                new KickScooter.Builder().withName("W005").build(),
                new KickScooter.Builder().withName("W006").build(),
                new KickScooter.Builder().withName("W007").build(),
                new KickScooter.Builder().withName("W008").build(),
                new KickScooter.Builder().withName("W009").build(),
                new KickScooter.Builder().withName("W010").build()
        };

        for (KickScooter kickScooter : kickScooters) {
            kickScooterRepository.save(kickScooter);
        }

    }
}
