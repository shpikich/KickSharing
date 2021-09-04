package ru.yushkov.kicksharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.repository.KickScooterRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class KickScooterServiceImpl implements KickScooterService {

    @Autowired
    private KickScooterRepository kickScooterRepository;

    @Override
    public KickScooter addKickScooter(KickScooter kickScooter) {
        return kickScooterRepository.save(kickScooter);
    }

    @Override
    public KickScooter deleteKickScooterById(Long id) {
        Optional<KickScooter> optionalKickScooter = kickScooterRepository.findById(id);
        if (optionalKickScooter.isPresent()) {
            kickScooterRepository.deleteById(id);
            return optionalKickScooter.get();
        }
        throw new NoSuchElementException("KickScooter with this id wasn't found");
    }
}
