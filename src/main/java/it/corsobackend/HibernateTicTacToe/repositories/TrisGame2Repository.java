package it.corsobackend.HibernateTicTacToe.repositories;

import it.corsobackend.HibernateTicTacToe.entities.TrisGameDAO2;
import org.springframework.data.repository.CrudRepository;

public interface TrisGame2Repository extends CrudRepository<TrisGameDAO2, String> {
}
