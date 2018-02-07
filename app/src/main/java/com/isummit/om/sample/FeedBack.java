package com.isummit.om.sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends AppCompatActivity {
TextView tvs;
    EditText editText, name;
    Button submit;
    String value="";
    DatabaseReference rootRef,demoRef;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait while fetching data..");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        Toolbar mToolbar =  findViewById(R.id.toolbar);
        mToolbar.setTitle("FeedBack");
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });

        tvs=(TextView)findViewById(R.id.feed);
        name =  findViewById(R.id.editText2);
        editText =  findViewById(R.id.tv_feedback);
        submit =  findViewById(R.id.btnSubmit);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        tvs.startAnimation(fadeInAnimation );
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("feedback");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                v.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }
                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        value = editText.getText().toString();
                        System.out.print("Feedback: "+value);
                        if(value.equals("")) {
                            Toast.makeText(FeedBack.this, "Enter the feedback first", Toast.LENGTH_SHORT).show();
                            return;
                        }
                            progress.show();

                            //push creates a unique id in database
                            demoRef.push().setValue(value);
                            progress.dismiss();
                            Toast.makeText(FeedBack.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
                            finish();

                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

            }
        });
    }
    }
