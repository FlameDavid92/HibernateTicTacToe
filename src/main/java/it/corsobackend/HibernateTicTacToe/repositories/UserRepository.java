package it.corsobackend.HibernateTicTacToe.repositories;

import it.corsobackend.HibernateTicTacToe.entities.UserDAO;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDAO, String> {
    Optional<UserDAO> findByUsername(String username);
}
