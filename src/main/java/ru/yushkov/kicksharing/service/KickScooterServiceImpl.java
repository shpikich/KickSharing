package ru.yushkov.kicksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.repository.KickScooterRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class KickScooterServiceImpl implements KickScooterService {

    @Autowired
    private KickScooterRepository kickScooterRepository;

    @Override
    public List<KickScooter> addKickScooters(List<KickScooter> kickScooters) {
        return (List<KickScooter>) kickScooterRepository.saveAll(kickScooters);
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
    public List<KickScooter> displayListOfScooters() {
        return (List<KickScooter>) kickScooterRepository.findAll();
    }
}
