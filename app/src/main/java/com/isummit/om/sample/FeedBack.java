package com.isummit.om.sample;

import android.app.ProgressDialog;
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
    EditText editText;
    Button submit;
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
        editText = (EditText) findViewById(R.id.tvValue);
        submit = (Button) findViewById(R.id.btnSubmit);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        tvs.startAnimation(fadeInAnimation );
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("feedback");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                String value = editText.getText().toString();
                //push creates a unique id in database
                demoRef.push().setValue(value);
                progress.dismiss();
                Toast.makeText(FeedBack.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }
