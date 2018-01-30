package com.isummit.om.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

public class summit13 extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("sixth");
    StorageReference storageReference= FirebaseStorage.getInstance().getReference("2013/bas_0076.jpg");
    StorageReference st= FirebaseStorage.getInstance().getReference("2013/group.jpg");
    ImageView imageView,imgs;
    TextView tvs,date,name,theme,venue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summit09);
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            myRef.keepSynced(true);
        }catch (Exception e){


        }




        imageView=(ImageView)findViewById(R.id.image);

        date=(TextView)findViewById(R.id.date);
        venue=(TextView)findViewById(R.id.venue);
        theme=(TextView)findViewById(R.id.theme);
        tvs=(TextView)findViewById(R.id.TextViews);
        imgs=(ImageView)findViewById(R.id.img2);



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
