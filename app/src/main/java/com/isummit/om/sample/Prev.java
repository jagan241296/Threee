package com.isummit.om.sample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.Transaction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prev extends AppCompatActivity {

    GridView grid;
    private List<String> web = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev);

        Toolbar mToolbar =  findViewById(R.id.toolbar);
        mToolbar.setTitle("3I Summits");
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                finish();
            }
        });
        web.add("3i Summit 2008");
        web.add("3i Summit 2009");
        web.add("3i Summit 2010");
        web.add("3i Summit 2011");
        web.add("3i Summit 2012");
        web.add("3i Summit 2013");
        web.add("3i Summit 2014");
        web.add("3i Summit 2015");
        web.add("3i Summit 2016");
        web.add("3i Summit 2018");



        CustomGrid adapter =new CustomGrid(Prev.this,web);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                    {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Prev.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Intent one=new Intent(Prev.this,summit08.class);
                        startActivity(one);
                        break;
                    }
                    case 1:
                    {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Prev.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent ones=new Intent(Prev.this,summit09.class);
                        startActivity(ones);
                        break;
                    }
                    case 2:
                    {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Prev.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent two=new Intent(Prev.this,summit10.class);
                        startActivity(two);
                        break;
                    }
                    case 3:
                    {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Prev.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent three=new Intent(Prev.this,summit11.class);
                        startActivity(three);
                        break;
                    }
                    case 4:
                    {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Prev.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent four=new Intent(Prev.this,summit12.class);
                        startActivity(four);
                        break;
                    }
                    case 5:
                    {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Prev.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent five=new Intent(Prev.this,summit13.class);
                        startActivity(five);
                        break;
                    }
                    case 6:
                    {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Prev.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent six=new Intent(Prev.this,summit14.class);
                        startActivity(six);
                        break;
                    }
                    case 7:
                    {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Prev.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent seven=new Intent(Prev.this,summit15.class);
                        startActivity(seven);
                        break;
                    }
                    case 8:
                    {
                        Intent eight=new Intent(Prev.this,summit16.class);
                        startActivity(eight);
                        break;
                    }
                    case 9:
                    {
                        Toast.makeText(getApplicationContext()," Will be Updated Soon",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default:
                    {
                        boolean netConnected=isNetworkAvailable();
                        if(netConnected==false)
                        {
                            Toast.makeText(Prev.this, "Network Error...",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                }
            }

        });
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

