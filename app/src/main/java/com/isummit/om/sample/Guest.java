package com.isummit.om.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Guest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

    }

    public void onCardClick(View view)
    {
        Intent guestList = new Intent(this, GuestListActivity.class);
        startActivity(guestList);
    }
}
