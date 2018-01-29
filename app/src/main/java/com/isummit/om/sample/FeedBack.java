package com.isummit.om.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends AppCompatActivity {
TextView tvs;
    EditText editText;
    Button submit;
    DatabaseReference rootRef,demoRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
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
                String value = editText.getText().toString();
                //push creates a unique id in database
                demoRef.push().setValue(value);

                Toast.makeText(FeedBack.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }
