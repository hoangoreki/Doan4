package com.example.genk;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private  String id;
    private  String Passwords;
    private  String Email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswords() {
        return Passwords;
    }

    public void setPasswords(String passwords) {
        Passwords = passwords;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
