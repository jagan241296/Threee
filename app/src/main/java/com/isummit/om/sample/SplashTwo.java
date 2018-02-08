package com.isummit.om.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashTwo extends AppCompatActivity {

    private ImageView imageViewLogo,imageViewWelcome,imageviewpic;
    private DatabaseReference dbs;
    private String val;
    String prefName = "userNamePref";
    String EVENT_TIME="event_time";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_two);

        dbs= FirebaseDatabase.getInstance().getReference("eventsdate");
        //Get Children record

        dbs.child("date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                val = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = prefs.edit();
                prefEditor.putString(EVENT_TIME, val);
                prefEditor.commit();
                System.out.println("Vlue before: "+val);

                Intent intent=new Intent(SplashTwo.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}
