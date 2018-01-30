package com.isummit.om.sample;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

public class summit08 extends AppCompatActivity {
    ImageView imageView,imgs,img2;
    TextView tvs,date,name,theme,venue;
    ImageButton imageButton;
    LayoutInflater layoutInflater;
    ViewGroup container;
    private ProgressDialog progress;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private StorageReference storageReference;
    private StorageReference st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summit08);
        Toolbar mToolbar =  findViewById(R.id.toolbar);
        mToolbar.setTitle("3I Summit 2008");
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });


        try {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("first");
            storageReference= FirebaseStorage.getInstance().getReference("2011/inaug.jpg");
            st= FirebaseStorage.getInstance().getReference("2011/picture_1.jpg");

            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            myRef.keepSynced(true);
        }catch (Exception e){
        }
        imageView=findViewById(R.id.image);

        date=findViewById(R.id.date);
       venue=findViewById(R.id.venue);
        theme=findViewById(R.id.theme);
        tvs=findViewById(R.id.TextViews);
        imgs=findViewById(R.id.img2);



        myRef.child("Speakers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val=dataSnapshot.getValue(String.class);
                tvs.setText(val);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.child("Date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val=dataSnapshot.getValue(String.class);
                date.setText(val);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRef.child("Venue").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val=dataSnapshot.getValue(String.class);
                venue.setText(val);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.child("Theme").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val=dataSnapshot.getValue(String.class);
                theme.setText(val);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Glide.with(this /* context */)
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(imageView);

        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(st)
                .into(imgs);






    }


}



