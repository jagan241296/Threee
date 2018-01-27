package com.isummit.om.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GuestListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        String[] myDataset = { "Jaggu", "Chandel", "Shashi Bhat", "Saurabh", "Onkar",
                "Rinu", "Vidya", "SreeRanjini", "Rohit",
                "Aman", "Lallu", "Saksham", "Yogesh" };

        mRecyclerView = findViewById(R.id.my_recycler_view);

        // getSupportActionBar().setIcon(R.drawable.ic_launcher);

        // getSupportActionBar().setTitle("Android Versions");

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CardViewDataAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onBClick(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( this);

        // set title
        alertDialogBuilder.setTitle("Profile");

        // set dialog message
        alertDialogBuilder
                .setMessage("You Selected Profile information of the Guest")
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    /*public void onBClick2(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( this);

        // set title
        alertDialogBuilder.setTitle("Status");

        // set dialog message
        alertDialogBuilder
                .setMessage("You Selected Status information of the Guest")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                       dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }*/

}
