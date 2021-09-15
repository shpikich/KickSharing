package ru.yushkov.kicksharing.service;

import org.springframework.stereotype.Service;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.repository.KickScooterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static ru.yushkov.kicksharing.entity.Status.ALL;
import static ru.yushkov.kicksharing.entity.Status.AVAILABLE;

@Service
public class KickScooterServiceImpl implements KickScooterService {

    private final KickScooterRepository kickScooterRepository;

    public KickScooterServiceImpl(KickScooterRepository kickScooterRepository) {
        this.kickScooterRepository = kickScooterRepository;
    }

    @Override
    public List<KickScooter> addKickScooters(List<KickScooter> kickScooters) {
        List<KickScooter> addedKickScooters = new ArrayList<>();
        for (KickScooter kickScooter : kickScooters) {
            KickScooter kickScooterWithStatus = new KickScooter.Builder()
                    .withName(kickScooter.getName())
                    .withStatus(AVAILABLE)
                    .build();
            addedKickScooters.add(kickScooterWithStatus);
        }
        return (List<KickScooter>) kickScooterRepository.saveAll(addedKickScooters);
    }

    @Override
    public void deleteKickScooterById(Long kickScooterId) {
        Optional<KickScooter> optionalKickScooter = kickScooterRepository.findById(kickScooterId);
        if (optionalKickScooter.isPresent()) {
            kickScooterRepository.deleteById(kickScooterId);
        } else {
            throw new NoSuchElementException("KickScooter with id " + kickScooterId + " wasn't found");
        }
    }

    @Override
    public List<KickScooter> getListOfKickScooters(String status) {
        List<KickScooter> listOfAllKickScooters = (List<KickScooter>) kickScooterRepository.findAll();
        List<KickScooter> listOfAvailableKickScooters = new ArrayList<>();
        if (status.equals(ALL.getCode())) {
            return listOfAllKickScooters;
        } else if (status.equals(AVAILABLE.getCode())) {
            for (KickScooter kickScooter : listOfAllKickScooters) {
                if (kickScooter.getStatus() == AVAILABLE) {
                    listOfAvailableKickScooters.add(kickScooter);
                }
            }
        }
        return listOfAvailableKickScooters;
    }
}
