package com.isummit.om.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class SplashDummy extends AppCompatActivity {
    String USERNAME_KEY ="UserName";
    String prefName = "userNamePref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Variables that store user credentials
        SharedPreferences userPrefs = getSharedPreferences(prefName, MODE_PRIVATE);
        String userName = userPrefs.getString(USERNAME_KEY, "");

        if(userName=="")
        {
            startActivity(new Intent(SplashDummy.this, Splash.class));
            finish();
        }
        else
        {
            startActivity(new Intent(SplashDummy.this, SplashTwo.class));
            finish();
        }
    }
}
