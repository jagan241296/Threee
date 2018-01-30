package com.isummit.om.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class GuestInfoDialogActivity extends Activity {

    private TextView tv_gname, tv_desig, tv_profile, tv_company;
    private Button bt_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guest_info_dialog);

        tv_gname=findViewById(R.id.tv_gname);
        tv_desig=findViewById(R.id.tv_desig);
        tv_company=findViewById(R.id.tv_company);
        tv_profile=findViewById(R.id.tv_profile);
        bt_status=findViewById(R.id.bt_status);

        //Get record from the Bundle in Intent
        Intent intent = getIntent();
        String record = intent.getExtras().getString("record");

        String[] record_spilt=record.split("-");
        /*Data arrangement:
        0 - Name
        1 - Designation
        2 - Company Name
        3 - Company's Profile/HR Profile
        4 - Arrival Status        */


        tv_gname.setText(record_spilt[0]);
        tv_desig.setText(record_spilt[1]);
        tv_company.setText(record_spilt[2]);
        tv_profile.setText(record_spilt[3]);
        bt_status.setText(record_spilt[4]);
    }
}
