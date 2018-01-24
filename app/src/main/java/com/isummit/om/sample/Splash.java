package com.isummit.om.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {
    private ProgressBar mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Get the progress bar handler
        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);
        new Thread(new Runnable() {
            public void run() {
                doBackgroundTask();
                startApp();
                finish();
            }
        }).start();
    }


    private void doBackgroundTask() {
        for (int progress=0; progress<=100; progress+=10) {
            try {
                Thread.sleep(200);
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
