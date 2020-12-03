package it.corsobackend.HibernateTicTacToe.repositories;

import it.corsobackend.HibernateTicTacToe.entities.CookieDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CookieRepository extends CrudRepository<CookieDAO,Long> {
    Optional<CookieDAO> findByCookie(String cookie);
}