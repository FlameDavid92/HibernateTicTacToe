package it.corsobackend.HibernateTicTacToe.views;

public class UserView {
    private final String username;
    private final String password;
    private final String telefono;
    public UserView(String username, String password, String telefono) {
        this.username = username;
        this.password = password;
        this.telefono = telefono;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getTelefono() {
        return telefono;
    }
}
