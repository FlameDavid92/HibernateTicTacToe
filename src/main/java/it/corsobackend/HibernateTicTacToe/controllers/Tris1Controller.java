package it.corsobackend.HibernateTicTacToe.controllers;

import it.corsobackend.HibernateTicTacToe.entities.CookieDAO;
import it.corsobackend.HibernateTicTacToe.repositories.TrisGameRepository;
import it.corsobackend.HibernateTicTacToe.services.TrisService;
import it.corsobackend.HibernateTicTacToe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tris1")
public class Tris1Controller {
    @Autowired private UserService us;
    @Autowired private TrisGameRepository tgr;

    @GetMapping("/new/{simboloPlayer}")
    String nuovoGioco(@Autowired TrisService ts,
                      @PathVariable("simboloPlayer") String simboloPlayer,
                      @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDAO cookieDAO = us.isLogged(auth);
        if (cookieDAO == null) return "Accesso protetto, effettua il login!";
        return ts.nuovoGioco(auth, simboloPlayer, tgr);
    }

    @GetMapping("/move/{posI}/{posJ}")
    String inserisciMossa(@Autowired TrisService ts,
                          @PathVariable("posI") int posI, @PathVariable("posJ") int posJ,
                          @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDAO cookieDAO = us.isLogged(auth);
        if (cookieDAO == null) return "Accesso protetto, effettua il login!";
        return ts.gioca(auth, posI, posJ, tgr);
    }

    @GetMapping("/back")
    String cancellaUltimaMossa(@Autowired TrisService ts,
                               @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDAO cookieDAO = us.isLogged(auth);
        if (cookieDAO == null) return "Accesso protetto, effettua il login!";
        return ts.back(auth, tgr);
    }

    @GetMapping("/state")
    String statoPartita(@Autowired TrisService ts,
                        @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDAO cookieDAO = us.isLogged(auth);
        if (cookieDAO == null) return "Accesso protetto, effettua il login!";
        return ts.statoAttuale(auth, tgr);
    }
}
