package it.corsobackend.HibernateTicTacToe.controllers;

import it.corsobackend.HibernateTicTacToe.entities.CookieDB;
import it.corsobackend.HibernateTicTacToe.services.TrisService;
import it.corsobackend.HibernateTicTacToe.services.TrisService2;
import it.corsobackend.HibernateTicTacToe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimiEserciziController {
    @Autowired
    private UserService us;

    @GetMapping("/{idPartita}/new/{simboloPlayer}")
    String nuovoGioco(@Autowired TrisService ts,
                      @PathVariable("idPartita") int idPartita,
                      @PathVariable("simboloPlayer") String simboloPlayer,
                      @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDB cookieDB = us.isLogged(auth);
        if (cookieDB == null) return "Accesso protetto, effettua il login!";
        return ts.nuovoGioco(idPartita, simboloPlayer);
    }

    @GetMapping("/{idPartita}/move/{posI}/{posJ}")
    String inserisciMossa(@Autowired TrisService ts, @PathVariable("idPartita") int idPartita,
                          @PathVariable("posI") int posI, @PathVariable("posJ") int posJ) {
        return ts.gioca(idPartita, posI, posJ);
    }

    @GetMapping("/{idPartita}/back")
    String cancellaUltimaMossa(@Autowired TrisService ts, @PathVariable("idPartita") int idPartita) {
        return ts.back(idPartita);
    }

    @GetMapping("/{idPartita}/state")
    String statoPartita(@Autowired TrisService ts, @PathVariable("idPartita") int idPartita) {
        return ts.statoAttuale(idPartita);
    }

    /*V2: Matrice di gioco come intero*/
    @GetMapping("/special/{idPartita}/new/{simboloPlayer}")
    String nuovoGioco2(@Autowired TrisService2 ts,
                       @PathVariable("idPartita") int idPartita,
                       @PathVariable("simboloPlayer") String simboloPlayer,
                       @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDB cookieDB = us.isLogged(auth);
        if (cookieDB == null) return "Accesso protetto, effettua il login!";
        return ts.nuovoGioco2(idPartita, simboloPlayer);
    }

    @GetMapping("/special/{idPartita}/move/{posI}/{posJ}")
    String inserisciMossa2(@Autowired TrisService2 ts, @PathVariable("idPartita") int idPartita,
                           @PathVariable("posI") int posI, @PathVariable("posJ") int posJ) {
        return ts.gioca2(idPartita, posI, posJ);
    }

    @GetMapping("/special/{idPartita}/back")
    String cancellaUltimaMossa2(@Autowired TrisService2 ts, @PathVariable("idPartita") int idPartita) {
        return ts.back2(idPartita);
    }

    @GetMapping("/special/{idPartita}/state")
    String statoPartita2(@Autowired TrisService2 ts, @PathVariable("idPartita") int idPartita) {
        return ts.statoAttuale2(idPartita);
    }

    /*Prova rappresentazione matrice di gioco con intero*/
    @GetMapping("/special/interotomatricetris/{interoMatrice}")
    String[][] generaMatrice(@Autowired TrisService2 ts, @PathVariable("interoMatrice") int interoMatrice) {
        return ts.generaMatrice(interoMatrice);
    }
}
