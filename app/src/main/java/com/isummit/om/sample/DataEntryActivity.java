package com.isummit.om.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataEntryActivity extends AppCompatActivity {

    private EditText tv1,tv2,tv3,tv4,tv5;
    private Button bt;
    String category;
    String name, desig,company,profile;
    String status="Not Checked";
    String record;
    private DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv4=findViewById(R.id.tv4);
        tv5=findViewById(R.id.tv5);
        bt=findViewById(R.id.bt2);

        rootRef = FirebaseDatabase.getInstance().getReference("Guests");
    }

    public void SaveData(View view)
    {
        name=tv2.getText().toString();
        desig=tv3.getText().toString();
        company=tv4.getText().toString();
        profile=tv5.getText().toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String format = simpleDateFormat.format(new Date());

        record=name + "-" +desig +  "-" + company + "-" + profile + "-" + status;

        category=tv1.getText().toString();

        rootRef.child(category).child(format).setValue(record);

        Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show();
    }
}
