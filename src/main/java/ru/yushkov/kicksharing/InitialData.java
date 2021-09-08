package ru.yushkov.kicksharing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.repository.KickScooterRepository;

import java.util.Arrays;

import static ru.yushkov.kicksharing.entity.Status.AVAILABLE;

@Component
public class InitialData implements CommandLineRunner {

    @Autowired
    private KickScooterRepository kickScooterRepository;

    @Override
    public void run(String... args) throws Exception {
        KickScooter[] kickScooters = {
                new KickScooter.Builder().withName("W001").withStatus(AVAILABLE).build(),
                new KickScooter.Builder().withName("W002").withStatus(AVAILABLE).build(),
                new KickScooter.Builder().withName("W003").withStatus(AVAILABLE).build(),
                new KickScooter.Builder().withName("W004").withStatus(AVAILABLE).build(),
                new KickScooter.Builder().withName("W005").withStatus(AVAILABLE).build(),
                new KickScooter.Builder().withName("W006").withStatus(AVAILABLE).build(),
                new KickScooter.Builder().withName("W007").withStatus(AVAILABLE).build(),
                new KickScooter.Builder().withName("W008").withStatus(AVAILABLE).build(),
                new KickScooter.Builder().withName("W009").withStatus(AVAILABLE).build(),
                new KickScooter.Builder().withName("W010").withStatus(AVAILABLE).build(),

        };

        kickScooterRepository.saveAll(Arrays.asList(kickScooters));

    }
}
