package com.isummit.om.developers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SplashTwo extends AppCompatActivity {

    private ImageView imageViewLogo,imageViewWelcome,imageviewpic;
    private DatabaseReference dbs;
    private String val;
    String prefName = "userNamePref";
    String EVENT_TIME="event_time";
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_two);
        //play music
        mp = MediaPlayer.create(getApplicationContext(), R.raw.welcome2);
        //local api's
        imageViewLogo=findViewById(R.id.three);
        imageViewWelcome=findViewById(R.id.welcome);
        imageviewpic=findViewById(R.id.welcome_gesture);

        //Adding animation to images
        Animation fromtop= AnimationUtils.loadAnimation(this,R.anim.slidefromtop);
        Animation frombottom=AnimationUtils.loadAnimation(this,R.anim.slidefrombottom);
        Animation fade=AnimationUtils.loadAnimation(this,R.anim.fade);
        imageViewLogo.startAnimation(fromtop);
        imageViewWelcome.startAnimation(frombottom);
        imageviewpic.startAnimation(fade);
        mp.start();
        boolean netConnected=isNetworkAvailable();
        if(netConnected==false)
        {
            val="23-02-2018-18-00-00";
            savePreferences();
        }
        else
        {
            getData();
        }

    }
    public void savePreferences()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = prefs.edit();
                prefEditor.putString(EVENT_TIME, val);
                prefEditor.commit();
                System.out.println("Event dadfgfdte at splash: "+val);
                Intent intent=new Intent(SplashTwo.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }

    public void getData()
    {
        dbs= FirebaseDatabase.getInstance().getReference("eventsdate");
        //Get Children record

        dbs.child("date").orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                val = dataSnapshot.getValue(String.class);
                savePreferences();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
