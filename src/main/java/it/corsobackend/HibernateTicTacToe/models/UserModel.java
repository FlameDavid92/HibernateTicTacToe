package it.corsobackend.HibernateTicTacToe.models;

public class UserModel {
    private final String username;
    private final String password;
    private final String telefono;
    public UserModel(String username, String password, String telefono) {
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
