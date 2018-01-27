package com.isummit.om.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {
    private static int SPLASH_SCREEN_OUT=1000;
    private ProgressBar mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Get the progress bar handler
          mProgress = findViewById(R.id.splash_screen_progress_bar);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doBackgroundTask();
                startApp();
                finish();
            }
        }).start();
    }
    private void doBackgroundTask() {
        for (int progress=0; progress<=100; progress+=1) {
            try {
                Thread.sleep(30);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(Splash.this, MainActivity.class);
        startActivity(intent);
    }




}
