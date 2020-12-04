package it.corsobackend.HibernateTicTacToe.controllers;

import it.corsobackend.HibernateTicTacToe.entities.CookieDAO;
import it.corsobackend.HibernateTicTacToe.repositories.TrisGame2Repository;
import it.corsobackend.HibernateTicTacToe.services.TrisService2;
import it.corsobackend.HibernateTicTacToe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tris2")
public class Tris2Controller {
    @Autowired private UserService us;
    @Autowired private TrisGame2Repository tg2r;

    /*V2: Matrice di gioco come intero*/
    @GetMapping("/new/{simboloPlayer}")
    String nuovoGioco2(@Autowired TrisService2 ts,
                       @PathVariable("simboloPlayer") String simboloPlayer,
                       @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDAO cookieDAO = us.isLogged(auth);
        if (cookieDAO == null) return "Accesso protetto, effettua il login!";
        return ts.nuovoGioco2(auth, simboloPlayer, tg2r);
    }

    @GetMapping("/move/{posI}/{posJ}")
    String inserisciMossa2(@Autowired TrisService2 ts,
                           @PathVariable("posI") int posI, @PathVariable("posJ") int posJ,
                           @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDAO cookieDAO = us.isLogged(auth);
        if (cookieDAO == null) return "Accesso protetto, effettua il login!";
        return ts.gioca2(auth, posI, posJ, tg2r);
    }

    @GetMapping("/back")
    String cancellaUltimaMossa2(@Autowired TrisService2 ts,
                                @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDAO cookieDAO = us.isLogged(auth);
        if (cookieDAO == null) return "Accesso protetto, effettua il login!";
        return ts.back2(auth, tg2r);
    }

    @GetMapping("/state")
    String statoPartita2(@Autowired TrisService2 ts,
                         @CookieValue(value = "auth", defaultValue = "") String auth) {
        CookieDAO cookieDAO = us.isLogged(auth);
        if (cookieDAO == null) return "Accesso protetto, effettua il login!";
        return ts.statoAttuale2(auth, tg2r);
    }

    /*Prova rappresentazione matrice di gioco con intero*/
    @GetMapping("/interotomatricetris/{interoMatrice}")
    String[][] generaMatrice(@Autowired TrisService2 ts, @PathVariable("interoMatrice") int interoMatrice) {
        return ts.generaMatrice(interoMatrice);
    }
}
