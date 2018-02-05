package com.isummit.om.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class Guest extends AppCompatActivity implements View.OnClickListener{
    private CardView hr,start_up,alumni,chief,igi_guests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        hr=findViewById(R.id.hr);
        start_up=findViewById(R.id.start_up);
        alumni=findViewById(R.id.alumni);
        chief=findViewById(R.id.chief);
        igi_guests=findViewById(R.id.igi_guests);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Guests");
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });

        hr.setOnClickListener(this);
        start_up.setOnClickListener(this);
        alumni.setOnClickListener(this);
        chief.setOnClickListener(this);
        igi_guests.setOnClickListener(this);

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
                        guestList = new Intent(Guest.this, GuestListActivity.class);
                        tv_category= findViewById(R.id.textView_1);
                        category=tv_category.getText().toString();
                        bundle.putString("category", category);
                        guestList.putExtras(bundle);
                        Toast.makeText(Guest.this,"category clicked: "+category,Toast.LENGTH_SHORT).show();
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
                        guestList = new Intent(Guest.this, GuestListActivity.class);
                        tv_category= findViewById(R.id.textView_2);
                        category=tv_category.getText().toString();
                        bundle.putString("category", category);
                        guestList.putExtras(bundle);
                        Toast.makeText(Guest.this,"category clicked: "+category,Toast.LENGTH_SHORT).show();
                        startActivity(guestList);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
            break;
            case R.id.alumni:
            {
                Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
                alumni.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        guestList = new Intent(Guest.this, GuestListActivity.class);
                        tv_category= findViewById(R.id.textView_3);
                        category=tv_category.getText().toString();
                        bundle.putString("category", category);
                        guestList.putExtras(bundle);
                        Toast.makeText(Guest.this,"category clicked: "+category,Toast.LENGTH_SHORT).show();
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
                        guestList = new Intent(Guest.this, GuestListActivity.class);
                        tv_category= findViewById(R.id.textView_4);
                        category=tv_category.getText().toString();
                        bundle.putString("category", category);
                        guestList.putExtras(bundle);
                        Toast.makeText(Guest.this,"category clicked: "+category,Toast.LENGTH_SHORT).show();
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
                        guestList = new Intent(Guest.this, GuestListActivity.class);
                        tv_category= findViewById(R.id.textView_5);
                        category=tv_category.getText().toString();
                        bundle.putString("category", category);
                        guestList.putExtras(bundle);
                        Toast.makeText(Guest.this,"category clicked: "+category,Toast.LENGTH_SHORT).show();
                        startActivity(guestList);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }
    }
}
