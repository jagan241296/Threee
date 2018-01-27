package com.isummit.om.sample;

/**
 * Created by Sai_Kameswari on 25-01-2018.
 */
public class Users {

    public String message;
    public String date;

    public Users() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Users(String username, String email) {
        this.message = username;
        this.date = email;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}