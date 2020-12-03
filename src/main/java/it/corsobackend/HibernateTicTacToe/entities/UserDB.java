package it.corsobackend.HibernateTicTacToe.entities;

import javax.persistence.*;

@Entity
public class UserDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    private String password;
    private String telefono;
    private Integer salt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CookieDB cookieDB;

    public UserDB() {
    }
    public UserDB(String username, String password, String telefono, Integer salt) {
        this.username = username;
        this.password = password;
        this.telefono = telefono;
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Integer getSalt() {
        return salt;
    }
    public CookieDB getCookie() {
        return cookieDB;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCookie(CookieDB cookieDB) {

        this.cookieDB = cookieDB;

    }
}
