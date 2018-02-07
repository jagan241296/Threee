package com.isummit.om.sample;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class summit14 extends AppCompatActivity {

    private TextView tvs,date,name,theme,venue,tv_loading;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private List<String> XMEN = new ArrayList<>();
    private List<String> XMENArray = new ArrayList<>();
    private DatabaseReference myRef;
    private String val,val_array[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summit14);
        Toolbar mToolbar = findViewById(R.id.toolbar);

        mToolbar.setTitle("3I Summit 2014");
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });



        date = findViewById(R.id.date);
        venue = findViewById(R.id.venue);
        theme = findViewById(R.id.theme);
        tvs = findViewById(R.id.TextViews);

        myRef=FirebaseDatabase.getInstance().getReference("seventh");
        myRef.child("Speakers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);
                tvs.setText(val);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.child("Date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);
                date.setText(val);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRef.child("Venue").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);
                venue.setText(val);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.child("Theme").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);
                theme.setText(val);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.child("imgs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                val = dataSnapshot.getValue(String.class);
                val_array=val.split("_");
                for(int i=0;i<val_array.length;i++)
                {
                    XMEN.add(val_array[i]);
                }
                //Remove loading text
                tv_loading=findViewById(R.id.tv_loading);
                tv_loading.setText("");

                init();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void init() {
        for(int i=0;i<XMEN.size();i++)
            XMENArray.add(XMEN.get(i));

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(summit14.this,XMENArray));
        CircleIndicator indicator =  findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3500, 3500);
    }

}



