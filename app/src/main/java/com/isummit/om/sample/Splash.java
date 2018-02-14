package com.isummit.om.sample;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {
    private ImageView imageViewLogo;
    private Button imageViewRegister;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //local api's
        imageViewLogo=findViewById(R.id.three_i);
        imageViewRegister=findViewById(R.id.sign_up);

        //Adding animation to images
        Animation fromtop= AnimationUtils.loadAnimation(this,R.anim.slidefromtop);
        Animation frombottom=AnimationUtils.loadAnimation(this,R.anim.slidefrombottom);
        imageViewLogo.startAnimation(fromtop);
        imageViewRegister.startAnimation(frombottom);

        //Setting OnClickListener for sign up button
        imageViewRegister.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Animation myAnim = AnimationUtils.loadAnimation(Splash.this, R.anim.bounce);
                imageViewRegister.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent=new Intent(Splash.this,RegistrationActivity.class);
                        Pair[] pairs=new Pair[1];
                        pairs[0]=new Pair<View,String>(imageViewLogo,"imageTransition");
                        ActivityOptions activityOptions=ActivityOptions.makeSceneTransitionAnimation(Splash.this, pairs);
                        startActivity(intent,activityOptions.toBundle());
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });
    }
}
