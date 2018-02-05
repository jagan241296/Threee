package com.isummit.om.sample;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashTwo extends AppCompatActivity {

    private ImageView imageViewLogo,imageViewWelcome,imageviewpic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_two);

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
                Intent intent=new Intent(SplashTwo.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}
