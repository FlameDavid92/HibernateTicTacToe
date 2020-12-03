package it.corsobackend.HibernateTicTacToe.services;

import it.corsobackend.HibernateTicTacToe.entities.CookieDB;
import it.corsobackend.HibernateTicTacToe.entities.UserDB;
import it.corsobackend.HibernateTicTacToe.repositories.CookieRepository;
import it.corsobackend.HibernateTicTacToe.repositories.UserRepository;
import it.corsobackend.HibernateTicTacToe.models.UserModel;
import it.corsobackend.HibernateTicTacToe.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;


@Service
public class UserService {
    @Autowired private UserRepository ur;
    @Autowired private CookieRepository cr;

    @Autowired SecurityService securityService;

    public boolean registration(UserView userview){
        UserModel userModel = new UserModel(userview.getUsername(), userview.getPassword(), userview.getTelefono());
        try{
            Integer salt = new Random().nextInt(50);
            ur.save(new UserDB(userModel.getUsername(),
                    securityService.computeHash(salt+userModel.getPassword()+salt),
                    userModel.getTelefono(),
                    salt));
            return true;
        }catch (DataIntegrityViolationException e){
            return false;
        }
    }

    public String login(UserView userview){
        UserModel userModel = new UserModel(userview.getUsername(), userview.getPassword(), userview.getTelefono());
        Optional<UserDB> optionalUserDB = ur.findByUsername(userModel.getUsername());
        if(optionalUserDB.isPresent()) {
            UserDB userDB = optionalUserDB.get();
            Integer salt = userDB.getSalt();
            String cryptPass = securityService.computeHash(salt+userModel.getPassword()+salt);
            if(cryptPass.equals(userDB.getPassword())) {
                String cookieValue = UUID.randomUUID().toString();
                CookieDB nuovoCookieDB = new CookieDB(cookieValue,userDB);
                userDB.setCookie(nuovoCookieDB);
                ur.save(userDB);
                return cookieValue;
            } else {
                /*Password errata*/
                return null;
            }
        }else{
            /*Non esiste l'utente con quello username*/
            return null;
        }
    }

    public boolean logout(String auth){
        Optional<CookieDB> optCookie = cr.findByCookie(auth);
        if(optCookie.isPresent()){
            UserDB userDB = optCookie.get().getUser();
            userDB.setCookie(null);
            cr.delete(optCookie.get());
            return true;
        }else return false;
    }

    public CookieDB isLogged(String auth){
        Optional<CookieDB> optCookie = cr.findByCookie(auth);
        return optCookie.orElse(null);
    }

    //registration
    //login
    //logout
}