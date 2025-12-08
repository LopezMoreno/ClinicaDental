package com.example.ClinicaDental.dto;

public class LoginRequest {
    public String username;
    public String password;

    //Constructor Vacío
    public LoginRequest(){}

    //Constructor con parámetros
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Getter and Setter
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
}
