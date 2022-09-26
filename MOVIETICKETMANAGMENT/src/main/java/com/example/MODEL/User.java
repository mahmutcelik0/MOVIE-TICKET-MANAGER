package com.example.MODEL;



//BU CLASS ICERISINDE USER OLUSTURULACAK. USER 2 YE AYRILACAK -> 1) ADMIN 2)UYE
/**
 * THIS IS USER CLASS THAT I CREATED ADMINS AND USERS THROUGH THIS CLASS.
 * BOTH OF THEM HAVE USERNAME AND PASSWORD AND ISADMIN BOOLEAN VARIABLE DETERMINES
 * ADMIN OR USER
 * */

public class User {
    private String username;
    private String password;
    private boolean isAdmin;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
