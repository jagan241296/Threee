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

public class GuestListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private  String category;
    private ProgressDialog  progress;
    private DatabaseReference rootRef;
    private List<String> names = new ArrayList<>();
    private List<String> company = new ArrayList<>();
    private List<String> names_url = new ArrayList<>();
    private List<String> record = new ArrayList<>();
    private String name="";
    private String [] split_data;
    private int status=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_guest);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Guests");
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Bundle bundle = getIntent().getExtras();
        category=bundle.getString("category");
        //Progress Bar
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait while fetching data..");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        // To dismiss the dialog


        switch (category)
        {
            case "Start-Up CEO's/Founder's/Co-Founder's":
            {
                category = "Start-Up CEO's";
                break;
            }
           case "IGI Dignitaries":
            {
                category = "IGI Guests";
                status = 1;
                break;
            }
        }
        //Get firebase instance
        rootRef= FirebaseDatabase.getInstance().getReference("Guests").child(category);
        //Get Children record
        names.clear();
        rootRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapShot:dataSnapshot.getChildren()) {

                    name=childSnapShot.getValue().toString();
                    split_data=name.split("_");
                    names.add(split_data[0]);
                    company.add(split_data[2]);
                    names_url.add(split_data[4]);
                    record.add(name);
                }
                setAdapterData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setAdapterData()
    {
        progress.dismiss();
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CardViewDataAdapter(names,company,record,names_url,getBaseContext(), status);
        mRecyclerView.setAdapter(mAdapter);

    }

}
