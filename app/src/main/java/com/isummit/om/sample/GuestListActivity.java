package com.isummit.om.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView tv_gname, tv_desig, tv_profile, tv_company;
    private Button bt_status;
    private DatabaseReference rootRef;
    private List<String> names = new ArrayList<>();
    private String name="";
    private String [] split_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Bundle bundle = getIntent().getExtras();
        category=bundle.getString("category");

        //Get firebase instance
        rootRef= FirebaseDatabase.getInstance().getReference("Guests").child(category);


        //Get Children record
        names.clear();
        rootRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapShot:dataSnapshot.getChildren()) {

                    name=childSnapShot.getValue().toString();
                    split_data=name.split("-");
                    names.add(split_data[0]);
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

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CardViewDataAdapter(names,getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onBClick(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( this);

        LayoutInflater inflater = this.getLayoutInflater();
        View content =  inflater.inflate(R.layout.layout_guest_profile_dialog, null);
        alertDialogBuilder.setView(content);
        // set title
        alertDialogBuilder.setTitle("Profile");
        alertDialogBuilder.setView(R.layout.layout_guest_profile_dialog);
        tv_gname=content.findViewById(R.id.tv_gname);
        tv_desig=content.findViewById(R.id.tv_desig);
        tv_company=content.findViewById(R.id.tv_company);
        tv_profile=content.findViewById(R.id.tv_profile);
        bt_status=content.findViewById(R.id.bt_status);

        tv_gname.setText("sx");

        // set dialog message
        alertDialogBuilder
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

}
