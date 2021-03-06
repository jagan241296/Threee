package com.isummit.om.developers;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeveloperActivity extends AppCompatActivity {

    private DatabaseReference rootRef;
    private List<String> developers_name, developers_clas, developers_image, developers_team;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String record="";
    private String[] split_data;
    private ProgressDialog progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        Toolbar mToolbar =  findViewById(R.id.toolbar);
        mToolbar.setTitle("Developers");
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

        developers_name = new ArrayList<>();
        developers_clas = new ArrayList<>();
        developers_image = new ArrayList<>();
        developers_team = new ArrayList<>();
        onActivityLoad();
    }

    public void FillListView()
    {
        progress.dismiss();
        mRecyclerView = findViewById(R.id.developer_recycler);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new DeveloperCardViewAdapter(this ,developers_name, developers_clas, developers_image, developers_team);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter( mAdapter );
    }

    public void onActivityLoad()
    {
        progress.show();
        rootRef = FirebaseDatabase.getInstance().getReference("developers");


        rootRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapShot:dataSnapshot.getChildren()) {
                    record=childSnapShot.getValue().toString();
                    split_data = record.split("_");
                    developers_name.add(split_data[0]);
                    developers_clas.add(split_data[1]);
                    developers_team.add(split_data[2]);
                    developers_image.add(split_data[3]);
                }
                FillListView();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}