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

    @GetMapping("/new/{simboloPlayer}")
    String nuovoGioco(@Autowired TrisService ts,
                      @PathVariable("simboloPlayer") String simboloPlayer,
                      @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDB cookieDB = us.isLogged(auth);
        if (cookieDB == null) return "Accesso protetto, effettua il login!";
        return ts.nuovoGioco(auth, simboloPlayer);
    }

    @GetMapping("/move/{posI}/{posJ}")
    String inserisciMossa(@Autowired TrisService ts,
                          @PathVariable("posI") int posI, @PathVariable("posJ") int posJ,
                          @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDB cookieDB = us.isLogged(auth);
        if (cookieDB == null) return "Accesso protetto, effettua il login!";
        return ts.gioca(auth, posI, posJ);
    }

    @GetMapping("/back")
    String cancellaUltimaMossa(@Autowired TrisService ts,
                               @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDB cookieDB = us.isLogged(auth);
        if (cookieDB == null) return "Accesso protetto, effettua il login!";
        return ts.back(auth);
    }

    @GetMapping("/state")
    String statoPartita(@Autowired TrisService ts,
                        @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDB cookieDB = us.isLogged(auth);
        if (cookieDB == null) return "Accesso protetto, effettua il login!";
        return ts.statoAttuale(auth);
    }
}
