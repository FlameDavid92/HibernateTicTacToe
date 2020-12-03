package it.corsobackend.HibernateTicTacToe.entities;

import javax.persistence.*;

@Entity
public class CookieDAO {
    @Id
    @Column(name = "user_id")
    private Long id;

    private String cookie;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserDAO user;

    public CookieDAO(){}
    public CookieDAO(String cookie, UserDAO user){
        this.cookie = cookie;
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    public UserDAO getUser() {
        return user;
    }
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
    public void setUser(UserDAO user) {
        this.user = user;
    }
}
