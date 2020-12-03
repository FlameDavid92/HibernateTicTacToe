package it.corsobackend.HibernateTicTacToe.repositories;

import it.corsobackend.HibernateTicTacToe.entities.CookieDB;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CookieRepository extends CrudRepository<CookieDB,Long> {
    Optional<CookieDB> findByCookie(String cookie);
}