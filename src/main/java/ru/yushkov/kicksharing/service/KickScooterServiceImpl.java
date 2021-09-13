package ru.yushkov.kicksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.repository.KickScooterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ru.yushkov.kicksharing.entity.Status.AVAILABLE;

@Service
public class KickScooterServiceImpl implements KickScooterService {

    @Autowired
    private KickScooterRepository kickScooterRepository;

    @Override
    public List<KickScooter> addKickScooters(List<KickScooter> kickScooters) {
        List<KickScooter> savedKickScooters = (List<KickScooter>) kickScooterRepository.saveAll(kickScooters);
        for (KickScooter kickScooter : savedKickScooters) {
            KickScooter kickScooterWithStatus = new KickScooter.Builder()
                    .withName(kickScooter.getName())
                    .withStatus(AVAILABLE)
                    .withId(kickScooter.getKickScooterId())
                    .build();
            kickScooterRepository.save(kickScooterWithStatus);
        }
        return (List<KickScooter>) kickScooterRepository.findAll();
    }

    @Override
    public KickScooter deleteKickScooterById(Long kickScooterId) {
        Optional<KickScooter> optionalKickScooter = kickScooterRepository.findById(kickScooterId);
        if (optionalKickScooter.isPresent()) {
            kickScooterRepository.deleteById(kickScooterId);
            return optionalKickScooter.get();
        }
        throw new NoSuchElementException("KickScooter with this id wasn't found");
    }

    @Override
    public List<KickScooter> displayListOfAllScooters() {
        return (List<KickScooter>) kickScooterRepository.findAll();
    }

    @Override
    public List<KickScooter> displayListOfFreeKickScooters() {
        List<KickScooter> listOfAllKickScooters = (List<KickScooter>) kickScooterRepository.findAll();
        List<KickScooter> listOfFreeKickScooters = new ArrayList<>();
        for (KickScooter kickScooter : listOfAllKickScooters) {
            if (kickScooter.getStatus() == AVAILABLE) {
                listOfFreeKickScooters.add(kickScooter);
            }
        }
        return listOfFreeKickScooters;
    }
}
