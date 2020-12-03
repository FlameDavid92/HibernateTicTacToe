package it.corsobackend.HibernateTicTacToe.repositories;

import it.corsobackend.HibernateTicTacToe.entities.TrisGameDAO;
import org.springframework.data.repository.CrudRepository;

public interface TrisGameRepository extends CrudRepository<TrisGameDAO,String> {
}
