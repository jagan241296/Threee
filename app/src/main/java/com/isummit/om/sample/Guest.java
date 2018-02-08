package com.isummit.om.sample;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Guest extends AppCompatActivity implements View.OnClickListener{
    private CardView hr,start_up,chief,igi_guests;
    private ArrayList<String> guest_array= new ArrayList<>();
    private String guest_count;
    private TextView textView, textView1, textView4, textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        hr=findViewById(R.id.hr);
        start_up=findViewById(R.id.start_up);
        chief=findViewById(R.id.chief);
        igi_guests=findViewById(R.id.igi_guests);

        textView=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView1);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);

        Toolbar mToolbar =  findViewById(R.id.toolbar);
        mToolbar.setTitle("Guests");
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });

        DatabaseReference rootRef=FirebaseDatabase.getInstance().getReference("guest_count");

        rootRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapShot: dataSnapshot.getChildren())
                {
                    guest_count=childSnapShot.getValue().toString();
                    System.out.println("Value: "+guest_count);
                    guest_array.add(guest_count);
                }
                setGuest();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hr.setOnClickListener(this);
        start_up.setOnClickListener(this);
        chief.setOnClickListener(this);
        igi_guests.setOnClickListener(this);

    }
    public void setGuest()
    {
         /*
        ALumni-textView3
        Chief guests-textView4
        HR-textView1
        IGI-textView5
        Start up-textView*/

        textView.setText(guest_array.get(4));
        textView1.setText(guest_array.get(2));
        textView4.setText(guest_array.get(1));
        textView5.setText(guest_array.get(3));
    }

    TextView tv_category;
    Intent guestList;
    Bundle bundle = new Bundle();
    String category;

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.hr:
            {
                Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
                hr.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Guest.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        guestList = new Intent(Guest.this, GuestListActivity.class);
                        tv_category= findViewById(R.id.textView_1);
                        category=tv_category.getText().toString();
                        bundle.putString("category", category);
                        guestList.putExtras(bundle);
                        startActivity(guestList);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }break;
            case R.id.start_up:
            {
                Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
                start_up.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Guest.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        guestList = new Intent(Guest.this, GuestListActivity.class);
                        tv_category= findViewById(R.id.textView_2);
                        category="Start-Up CEO's";
                        bundle.putString("category", category);
                        guestList.putExtras(bundle);
                        startActivity(guestList);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
            break;
            case R.id.chief:
            {
                Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
                chief.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Guest.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        guestList = new Intent(Guest.this, GuestListActivity.class);
                        tv_category= findViewById(R.id.textView_4);
                        category=tv_category.getText().toString();
                        bundle.putString("category", category);
                        guestList.putExtras(bundle);
                        startActivity(guestList);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
            break;
            case R.id.igi_guests:
            {
                Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
                igi_guests.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Guest.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        guestList = new Intent(Guest.this, GuestListActivity.class);
                        tv_category= findViewById(R.id.textView_5);
                        category="IGI Guests";
                        bundle.putString("category", category);
                        guestList.putExtras(bundle);
                        startActivity(guestList);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
