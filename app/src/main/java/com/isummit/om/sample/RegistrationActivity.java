package com.isummit.om.sample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationActivity extends AppCompatActivity {
    private EditText email, mobile,fullname;
    private CardView register;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    String USERNAME_KEY ="UserName";
    String EMAIL_KEY = "email";
    String CONTACT = "mobile";
    String prefName = "userNamePref",record;
    private DatabaseReference rootRef;
    String email_value,mobile_value,fullnam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        progressBar=findViewById(R.id.progressBar);

        fullname=findViewById(R.id.username);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        register=findViewById(R.id.register_button);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View v) {

                Animation myAnim = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.bounce);
                register.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        progressBar.setVisibility(v.VISIBLE);


                        fullnam=fullname.getText().toString();
                        email_value=email.getText().toString();
                        mobile_value=mobile.getText().toString();

                        if (TextUtils.isEmpty(fullnam)) {
                            Toast.makeText(getApplicationContext(), "Enter a name", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(v.GONE);
                            return;
                        }

                        if (TextUtils.isEmpty(email_value)) {
                            Toast.makeText(getApplicationContext(), "Enter your Email ID!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(v.GONE);
                            return;
                        }

                        if (TextUtils.isEmpty(mobile_value)) {
                            Toast.makeText(getApplicationContext(), "Enter a mobile number!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(v.GONE);
                            return;
                        }

                        if (mobile_value.length() < 10 || mobile_value.length() > 10) {
                            Toast.makeText(getApplicationContext(), "Enter Valid Mobile number", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(v.GONE);
                            return;
                        }

                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(RegistrationActivity.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(v.GONE);
                            return;
                        }
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                        String format = simpleDateFormat.format(new Date());
                        rootRef= FirebaseDatabase.getInstance().getReference("Registered");
                        record=fullnam+"_"+email_value+"_"+mobile_value;
                        rootRef.child(format).setValue(record);
                        rootRef.child(format).setValue(record, new DatabaseReference.CompletionListener() {
                            public void onComplete(DatabaseError error, DatabaseReference ref) {
                                progressBar.setVisibility(View.GONE);
                                savePreferences();
                            }
                        });
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    public void savePreferences(){
        SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString(USERNAME_KEY, fullnam);
        prefEditor.putString(EMAIL_KEY, email_value);
        prefEditor.putString(CONTACT, mobile_value);
        prefEditor.commit();
        Intent intent = new Intent(RegistrationActivity.this, SplashTwo.class);
        //finish all previous activities
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finishAffinity();
        startActivity(intent);
        finish();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
