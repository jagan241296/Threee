package com.isummit.om.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Testimonials extends AppCompatActivity {
    private static final String TAG = "a";
    DatabaseReference rootRef,demoRef;
    TextView tvvalue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimonials);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
            tvvalue=(TextView)findViewById(R.id.tvValue);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(Testimonials.this);

                alert.setTitle("Add a Testimonial");

                // Set an EditText view to get user input
                final EditText input = new EditText(Testimonials.this);
                alert.setView(input);
                rootRef = FirebaseDatabase.getInstance().getReference("testimonials");
                //database reference pointing to demo node
                demoRef = rootRef.child("messages");

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value;
                     value = input.getText().toString();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                        String format = simpleDateFormat.format(new Date());
                        Users user = new Users(value, format);
                        demoRef.child(value).setValue(user);
                        Toast.makeText(getApplicationContext(),"Testimonial added Successfully",Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();                    }
                });

                /*demoRef.child("value").addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users value = dataSnapshot.getValue(Users.class);
                tvvalue.setText((CharSequence) value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/

                alert.show();
            }
        });

    }

}