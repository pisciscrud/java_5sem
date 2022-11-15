package by.stalmakhova.repositories;

import by.stalmakhova.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByLogin(String  login);
    Boolean existsByLogin(String login);
}
