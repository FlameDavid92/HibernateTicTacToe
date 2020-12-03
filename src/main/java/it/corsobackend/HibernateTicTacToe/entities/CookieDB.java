package it.corsobackend.HibernateTicTacToe.entities;

import javax.persistence.*;

@Entity
public class CookieDB {
    @Id
    @Column(name = "user_id")
    private Long id;
    private String cookie;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserDB user;

    public CookieDB(){}
    public CookieDB(String cookie, UserDB user){
        this.cookie = cookie;
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    public UserDB getUser() {
        return user;
    }
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
    public void setUser(UserDB user) {
        this.user = user;
    }
}
