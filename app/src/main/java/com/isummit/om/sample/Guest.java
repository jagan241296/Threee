package com.isummit.om.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Guest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

    }
    TextView tv_category;
    Intent guestList;
    Bundle bundle = new Bundle();
    String category;
    public void onCardClickHR(View view)
    {
        guestList = new Intent(this, GuestListActivity.class);
        tv_category= findViewById(R.id.textView_1);
        category=tv_category.getText().toString();
        bundle.putString("category", category);
        guestList.putExtras(bundle);
        Toast.makeText(this,"category clicked: "+category,Toast.LENGTH_SHORT).show();
        startActivity(guestList);
    }

    public void onCardClickCEO(View view)
    {
        guestList = new Intent(this, GuestListActivity.class);
        tv_category= findViewById(R.id.textView_2);
        category=tv_category.getText().toString();
        bundle.putString("category", category);
        guestList.putExtras(bundle);
        Toast.makeText(this,"category clicked: "+category,Toast.LENGTH_SHORT).show();
        startActivity(guestList);
    }

    public void onCardClickAlumni(View view)
    {
        guestList = new Intent(this, GuestListActivity.class);
        tv_category= findViewById(R.id.textView_3);
        category=tv_category.getText().toString();
        bundle.putString("category", category);
        guestList.putExtras(bundle);
        Toast.makeText(this,"category clicked: "+category,Toast.LENGTH_SHORT).show();
        startActivity(guestList);
    }

    public void onCardClickChief(View view)
    {
        guestList = new Intent(this, GuestListActivity.class);
        tv_category= findViewById(R.id.textView_4);
        category=tv_category.getText().toString();
        bundle.putString("category", category);
        guestList.putExtras(bundle);
        Toast.makeText(this,"category clicked: "+category,Toast.LENGTH_SHORT).show();
        startActivity(guestList);
    }

    public void onCardClickIGI(View view)
    {
        guestList = new Intent(this, GuestListActivity.class);
        tv_category= findViewById(R.id.textView_5);
        category=tv_category.getText().toString();
        bundle.putString("category", category);
        guestList.putExtras(bundle);
        Toast.makeText(this,"category clicked: "+category,Toast.LENGTH_SHORT).show();
        startActivity(guestList);
    }
}
