package com.isummit.om.sample;

/**
 * Created by Sai_Kameswari on 25-01-2018.
 */
public class Users {

    public String message;


    public Users() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Users(String username, String email) {
        this.message = username;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}