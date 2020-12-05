package it.corsobackend.HibernateTicTacToe.entities;

import javax.persistence.*;

@Entity
public class UserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    private String password;
    private String telefono;
    private Integer salt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private CookieDAO cookieDAO;

    public UserDAO() {
    }
    public UserDAO(String username, String password, String telefono, Integer salt) {
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
    public CookieDAO getCookie() {
        return cookieDAO;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCookie(CookieDAO cookieDAO) {

        this.cookieDAO = cookieDAO;

    }
}
