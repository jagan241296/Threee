package com.isummit.om.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.Transaction;

public class Prev extends AppCompatActivity {

    GridView grid;
    String[] web={

            "3i Summit '08",
            "3i Summit '09",
            "3i Summit '10",
            "3i Summit '11",
            "3i Summit '12",
            "3i Summit '13",
            "3i Summit '14",
            "3i Summit '15",
            "3i Summit '16",
            "3i Summit '17"

    };


    int[] imageid={
            R.drawable.counter


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev);

        CustomGrid adapter =new CustomGrid(Prev.this,web, imageid);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    Intent one=new Intent(Prev.this,summit08.class);
                    startActivity(one);


                }
                if (position == 1) {
                    Intent ones=new Intent(Prev.this,summit09.class);
                    startActivity(ones);
                }
                if (position == 2) {
                    Intent two=new Intent(Prev.this,summit10.class);
                    startActivity(two);
                }

                if(position==3){
                    Intent three=new Intent(Prev.this,summit11.class);
                    startActivity(three);
                }

                if(position==4){
                    Intent four=new Intent(Prev.this,summit12.class);
                    startActivity(four);
                }

                if(position==5){
                    Intent five=new Intent(Prev.this,summit13.class);
                    startActivity(five);
                }

                if(position==6){

                }
                if(position==7){

                }
                if(position==8){

                }
                if(position==9){

                }
                else{
                }
            }

        });
    }
}

