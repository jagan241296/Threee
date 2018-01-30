package com.isummit.om.sample;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Testimonials extends AppCompatActivity {

    private DatabaseReference rootRef;
    private List<String> testimonials = new ArrayList<>();
    private List<String> testimonials_date = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String name="";
    String[] split_data;
    private ProgressDialog progress;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Testimoni");
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Your code
                finish();
            }
        });

        //Progress Bar
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait while fetching data..");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog


        onActivityLoad();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(Testimonials.this);

                alert.setTitle("Add a Testimonial");

                // Set an EditText view to get user input
                final EditText input = new EditText(Testimonials.this);
                alert.setView(input);
                progress.show();
                rootRef = FirebaseDatabase.getInstance().getReference("testimonials");
                //database reference pointing to demo node
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value,format,message;
                        value = input.getText().toString().trim();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                        format = simpleDateFormat.format(new Date());
                        message=value+"_"+format;
                        rootRef.child(format.toString()).setValue(message);
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),"Testimonial added Successfully",Toast.LENGTH_SHORT).show();
                        onDataChanged();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();                    }
                });

                alert.show();
            }
        });
    }

    public void onDataChanged()
    {
        progress.show();
        rootRef = FirebaseDatabase.getInstance().getReference("testimonials");
        testimonials.clear();
        testimonials_date.clear();
        rootRef.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot childSnapShot:dataSnapshot.getChildren()) {
                    name=childSnapShot.getValue().toString();
                    split_data=name.split("_");
                    testimonials.add(split_data[0]);
                    testimonials_date.add(split_data[1]);
                }
                FillListView();
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }

    public void FillListView()
    {
        progress.dismiss();
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new TestimonialCardViewAdapter(testimonials, testimonials_date);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter( mAdapter );
    }

    public void onActivityLoad()
    {
        progress.show();
        rootRef = FirebaseDatabase.getInstance().getReference("testimonials");
        testimonials.clear();
        testimonials_date.clear();

        rootRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapShot:dataSnapshot.getChildren()) {
                    name=childSnapShot.getValue().toString();
                    split_data=name.split("_");
                    //System.out.println("data split: "+split_data[0]+"Next: "+split_data[1]);
                    testimonials.add(split_data[0]);
                    testimonials_date.add(split_data[1]);

                }
                FillListView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}