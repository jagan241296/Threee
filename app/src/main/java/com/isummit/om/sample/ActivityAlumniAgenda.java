package com.isummit.om.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityAlumniAgenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_agenda);
        getSupportActionBar().setTitle( "Alumni Agenda" );
    }
}
