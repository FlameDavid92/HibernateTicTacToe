package it.corsobackend.HibernateTicTacToe.controllers;

import it.corsobackend.HibernateTicTacToe.repositories.CookieRepository;
import it.corsobackend.HibernateTicTacToe.repositories.UserRepository;
import it.corsobackend.HibernateTicTacToe.services.UserService;
import it.corsobackend.HibernateTicTacToe.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ControllerAuth {
    @Autowired private UserRepository ur;
    @Autowired private CookieRepository cr;
    @Autowired private UserService us;

    @PostMapping("/registrazione")
    ResponseEntity<String> registrazione (@RequestBody UserView userview){
        if(us.registration(userview)){
            return new ResponseEntity<>("Registrazione avvenuta con successo!", new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Registrazione fallita: username già presente.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody UserView userview, HttpServletResponse response){
        String cookieValue = us.login(userview);
        if(cookieValue != null){
            Cookie auth = new Cookie("auth", cookieValue);
            response.addCookie(auth);
            return new ResponseEntity<>("Login effettuato.",new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Login fallito.",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/logout")
    ResponseEntity<String> logout(@CookieValue(value = "auth", defaultValue = "") String auth){
        if(auth.equals("")) return new ResponseEntity<>("",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        else{
            if(us.logout(auth)) return new ResponseEntity<>("Logout effettuato.",new HttpHeaders(), HttpStatus.OK);
            else return new ResponseEntity<>("",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }
}
