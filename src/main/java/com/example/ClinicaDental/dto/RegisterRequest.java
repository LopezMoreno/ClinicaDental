package com.example.ClinicaDental.dto;

public class RegisterRequest {
    private String username;
    private String password;
    private String email;

    //Constructor vacío
    public RegisterRequest(){}

    //Constructor con parámetros
    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //Getter and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
