package it.corsobackend.HibernateTicTacToe.controllers;

import it.corsobackend.HibernateTicTacToe.entities.CookieDAO;
import it.corsobackend.HibernateTicTacToe.services.TrisService2;
import it.corsobackend.HibernateTicTacToe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tris2")
public class Tris2Controller {
    @Autowired private UserService us;

    /*V2: Matrice di gioco come intero*/
    @GetMapping("/{idPartita}/new/{simboloPlayer}")
    String nuovoGioco2(@Autowired TrisService2 ts,
                       @PathVariable("idPartita") int idPartita,
                       @PathVariable("simboloPlayer") String simboloPlayer,
                       @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDAO cookieDAO = us.isLogged(auth);
        if (cookieDAO == null) return "Accesso protetto, effettua il login!";
        return ts.nuovoGioco2(idPartita, simboloPlayer);
    }

    @GetMapping("/{idPartita}/move/{posI}/{posJ}")
    String inserisciMossa2(@Autowired TrisService2 ts, @PathVariable("idPartita") int idPartita,
                           @PathVariable("posI") int posI, @PathVariable("posJ") int posJ) {
        return ts.gioca2(idPartita, posI, posJ);
    }

    @GetMapping("/{idPartita}/back")
    String cancellaUltimaMossa2(@Autowired TrisService2 ts, @PathVariable("idPartita") int idPartita) {
        return ts.back2(idPartita);
    }

    @GetMapping("/{idPartita}/state")
    String statoPartita2(@Autowired TrisService2 ts, @PathVariable("idPartita") int idPartita) {
        return ts.statoAttuale2(idPartita);
    }

    /*Prova rappresentazione matrice di gioco con intero*/
    @GetMapping("/interotomatricetris/{interoMatrice}")
    String[][] generaMatrice(@Autowired TrisService2 ts, @PathVariable("interoMatrice") int interoMatrice) {
        return ts.generaMatrice(interoMatrice);
    }
}
