package com.isummit.om.sample;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ActivityAlumniAgenda extends AppCompatActivity {

    private DatabaseReference rootRef;
    private List<String> agenda_time = new ArrayList<>();
    private List<String> agenda_msg = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog progress;
    private String record;
    private String[] record_spilt;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_recycler);
        Toolbar mToolbar =  findViewById(R.id.toolbar);
        mToolbar.setTitle("Alumni Agenda");
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
    }
    public void FillListView()
    {
        progress.dismiss();
        mRecyclerView = findViewById(R.id.agenda_cycle);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new AgendaRecyclerAdapterAlumni(this, agenda_time, agenda_msg);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter( mAdapter );
    }

    public void onActivityLoad()
    {
        progress.show();
        rootRef = FirebaseDatabase.getInstance().getReference("Agenda");
        agenda_time.clear();
        rootRef.child("Alumni").orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapShot:dataSnapshot.getChildren()) {
                    record = childSnapShot.getValue().toString();
                    record_spilt=record.split("_");
                    agenda_time.add(record_spilt[0]);
                    agenda_msg.add(record_spilt[1]);
                }
                FillListView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
