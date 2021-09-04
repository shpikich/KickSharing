package ru.yushkov.kicksharing.repository;

import org.springframework.data.repository.CrudRepository;
import ru.yushkov.kicksharing.entity.KickScooter;

public interface KickScooterRepository extends CrudRepository<KickScooter, Long> {
}
