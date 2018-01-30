package com.isummit.om.sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Splash extends AppCompatActivity {
    private ProgressBar mProgress;
    private FirebaseAuth auth;
    String USERNAME_KEY ="UserName";
    String prefName = "userNamePref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Get the progress bar handler
        mProgress = findViewById(R.id.splash_screen_progress_bar);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                new AsyncTaskRunner().execute();
            }
        }).start();
    }

    private class AsyncTaskRunner extends AsyncTask<String, Integer, Integer> {


        @Override
        protected Integer doInBackground(String... params) {

            int count = 10;
            for (int i = 0; i < count; i++) {

                // Update the progress bar after every step
                int progress = (int) ((i / (float) count) * 100);
                publishProgress(progress);

                // Do some long loading things
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignore) {
                }

            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... value) {
            super.onProgressUpdate(value);
            mProgress.setProgress(value[0]);
        }



        @Override
        protected void onPostExecute(Integer result) {
            System.out.println("Reached post execute");

            SharedPreferences userPrefs = getSharedPreferences(prefName, MODE_PRIVATE);
            String userName = userPrefs.getString(USERNAME_KEY, "");

            if(userName=="")
            {
                startActivity(new Intent(Splash.this, RegistrationActivity.class));
                finish();
            }
            else
            {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }

        }
    }
}
