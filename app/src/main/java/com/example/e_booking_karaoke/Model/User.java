package com.example.e_booking_karaoke.Model;

//this is very simple class and it only contains the user attributes, a constructor and the getters
// you can easily do this by right click -> generate -> constructor and getters
public class User {

    private String iduser ,username, email,noHp,password,status;




    public User(String iduser, String username, String email, String noHp, String password, String status) {
        this.iduser = iduser;
        this.username = username;
        this.email = email;
        this.noHp = noHp;
        this.password= password;
        this.status = status;

    }

    public String getIdUser() {
        return iduser;
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getNoHp() {
        return noHp;
    }
    public String getPassword() {
        return password;
    }
    public String getStatus() {
        return status;
    }


    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}