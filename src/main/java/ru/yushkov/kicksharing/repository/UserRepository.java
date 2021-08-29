package ru.yushkov.kicksharing.repository;

import org.springframework.data.repository.CrudRepository;
import ru.yushkov.kicksharing.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
