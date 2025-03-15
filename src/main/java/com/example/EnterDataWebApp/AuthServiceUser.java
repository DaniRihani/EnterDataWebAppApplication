package com.example.EnterDataWebApp;


public class AuthServiceUser {
    public String username;
    public String password;

    public AuthServiceUser() {
        this.username = "";
        this.password = "";
    }

    public AuthServiceUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthServiceUser that = (AuthServiceUser) o;
        return username.equals(that.username) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(username, password);
    }

}