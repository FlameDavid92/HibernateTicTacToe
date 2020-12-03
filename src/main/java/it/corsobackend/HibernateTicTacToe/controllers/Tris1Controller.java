package it.corsobackend.HibernateTicTacToe.controllers;

import it.corsobackend.HibernateTicTacToe.entities.CookieDB;
import it.corsobackend.HibernateTicTacToe.services.TrisService;
import it.corsobackend.HibernateTicTacToe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tris1")
public class Tris1Controller {
    @Autowired private UserService us;

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
                          @PathVariable("posI") int posI, @PathVariable("posJ") int posJ,
                          @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDB cookieDB = us.isLogged(auth);
        if (cookieDB == null) return "Accesso protetto, effettua il login!";
        return ts.gioca(idPartita, posI, posJ);
    }

    @GetMapping("/{idPartita}/back")
    String cancellaUltimaMossa(@Autowired TrisService ts,
                               @PathVariable("idPartita") int idPartita,
                               @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDB cookieDB = us.isLogged(auth);
        if (cookieDB == null) return "Accesso protetto, effettua il login!";
        return ts.back(idPartita);
    }

    @GetMapping("/{idPartita}/state")
    String statoPartita(@Autowired TrisService ts,
                        @PathVariable("idPartita") int idPartita,
                        @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDB cookieDB = us.isLogged(auth);
        if (cookieDB == null) return "Accesso protetto, effettua il login!";
        return ts.statoAttuale(idPartita);
    }
}
