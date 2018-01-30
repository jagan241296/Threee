package com.isummit.om.sample;

import android.widget.TextView;

/**
 * Created by Sai_Kameswari on 30-01-2018.
 */
public class User {

    public String username;
    public String email;

    public User(TextView username, TextView user_email) {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}