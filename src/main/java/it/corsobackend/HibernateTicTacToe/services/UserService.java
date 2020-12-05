package it.corsobackend.HibernateTicTacToe.services;

import it.corsobackend.HibernateTicTacToe.entities.CookieDAO;
import it.corsobackend.HibernateTicTacToe.entities.UserDAO;
import it.corsobackend.HibernateTicTacToe.repositories.CookieRepository;
import it.corsobackend.HibernateTicTacToe.repositories.UserRepository;
import it.corsobackend.HibernateTicTacToe.models.UserModel;
import it.corsobackend.HibernateTicTacToe.views.UserView;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;


@Service
public class UserService {
    @Autowired
    private UserRepository ur;
    @Autowired
    private CookieRepository cr;

    @Autowired
    SecurityService securityService;

    @PersistenceContext
    private EntityManager entityManager;

    public boolean registration(UserView userview) {
        UserModel userModel = new UserModel(userview.getUsername(), userview.getPassword(), userview.getTelefono());
        try {
            Integer salt = new Random().nextInt(50);
            ur.save(new UserDAO(userModel.getUsername(),
                    securityService.computeHash(salt + userModel.getPassword() + salt),
                    userModel.getTelefono(),
                    salt));
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public String login(UserView userview) {
        //modificare cookie vecchio in caso di utente loggato

        UserModel userModel = new UserModel(userview.getUsername(), userview.getPassword(), userview.getTelefono());
        Optional<UserDAO> optionalUserDB = ur.findByUsername(userModel.getUsername());

        if (optionalUserDB.isPresent()) {
            UserDAO userDAO = optionalUserDB.get();
            Integer salt = userDAO.getSalt();
            String cryptPass = securityService.computeHash(salt + userModel.getPassword() + salt);
            if (cryptPass.equals(userDAO.getPassword())) {
                String cookieValue = UUID.randomUUID().toString();

                if (userDAO.getCookie() == null) {
                    CookieDAO nuovoCookieDAO = new CookieDAO(cookieValue, userDAO);
                    userDAO.setCookie(nuovoCookieDAO);
                }else{
                    //userDAO.getCookie().setCookie(cookieValue); /*ritorna un nuovo cookie*/
                    return userDAO.getCookie().getCookie(); /*ritorna vecchio cookie*/
                }
                ur.save(userDAO);
                return cookieValue;
            } else {
                /*Password errata*/
                return null;
            }
        } else {
            /*Non esiste l'utente con quello username*/
            return null;
        }
    }

    public boolean logout(String auth) {
        Optional<CookieDAO> optCookie = cr.findByCookie(auth);
        if (optCookie.isPresent()) {
            UserDAO userDAO = optCookie.get().getUser();
            userDAO.setCookie(null);
            cr.delete(optCookie.get());
            return true;
        } else return false;
    }

    public CookieDAO isLogged(String auth) {
        Optional<CookieDAO> optCookie = cr.findByCookie(auth);
        return optCookie.orElse(null);
    }

    //registration
    //login
    //logout
}
