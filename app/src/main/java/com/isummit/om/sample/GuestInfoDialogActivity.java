package com.isummit.om.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class GuestInfoDialogActivity extends Activity {

    private TextView tv_gname, tv_desig, tv_profile, tv_company, txtview;
    private Button bt_status;
    private ImageView img2,imgviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_guest_profile_dialog);

        tv_gname=findViewById(R.id.tv_gname);
        tv_desig=findViewById(R.id.tv_desig);
        tv_company=findViewById(R.id.tv_company);
        tv_profile=findViewById(R.id.tv_profile);
        imgviews=findViewById(R.id.imgviews);
        txtview=findViewById(R.id.txtview);
        img2=findViewById(R.id.imageView2);
        bt_status=findViewById(R.id.btnOk);

        //Get record from the Bundle in Intent
        Intent intent = getIntent();
        String record = intent.getExtras().getString("record");

        System.out.println("Record: "+record);

        String[] record_spilt=record.split("_");

        /*Data arrangement:
        0 - Name
        1 - Designation
        2 - Company Name
        3 - Company's Profile/HR Profile
        4 - Photo URL
        5 - Arrival Status        */

        Picasso.with(getApplicationContext()).load(record_spilt[4]).into(img2);
        tv_gname.setText(record_spilt[0]);
        tv_desig.setText(record_spilt[1]);
        tv_company.setText(record_spilt[2]);
        tv_profile.setText(record_spilt[3]);

        if(record_spilt[5].equals("Checked In"))
        {
            Picasso.with(getApplicationContext()).load(R.drawable.double_click).into(imgviews);
            txtview.setText("Checked IN");
        }

    }
    public void closeDialog(View view)
    {
        finish();
    }
}
