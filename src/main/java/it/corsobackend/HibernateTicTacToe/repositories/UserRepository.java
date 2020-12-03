package it.corsobackend.HibernateTicTacToe.repositories;

import it.corsobackend.HibernateTicTacToe.entities.UserDB;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDB, String> {
    Optional<UserDB> findByUsername(String username);
}
