package com.isummit.om.sample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class IGIGuestActivity extends Activity {

    private TextView tv_gname_igi, tv_desig_igi, tv_company_igi, txtview_igi;
    private CardView bt_status_igi;
    private ImageView img2_igi,imgviews_igi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_igiguest);




        tv_gname_igi=findViewById(R.id.tv_gname_igi);
            tv_desig_igi=findViewById(R.id.tv_desig_igi);
            tv_company_igi=findViewById(R.id.tv_company_igi);
            imgviews_igi=findViewById(R.id.imgviews_igi);
            txtview_igi=findViewById(R.id.txtview_igi);
            img2_igi=findViewById(R.id.imageView2_igi);
            bt_status_igi=findViewById(R.id.btnOk_igi);

            //Get record from the Bundle in Intent
            Intent intent = getIntent();
            String record = intent.getExtras().getString("record");

            System.out.println("Record: "+record);

            String[] record_spilt=record.split("_");

        /*Data arrangement:
        0 - Name
        1 - Designation
        2 - Company Name
        3 - profile
        4 - Photo URL
        5 - Arrival Status --Not Checked       */

            Picasso.with(getApplicationContext()).load(record_spilt[4]).into(img2_igi);
            tv_gname_igi.setText(record_spilt[0]);
            tv_desig_igi.setText("( "+record_spilt[1]+" )");
            tv_company_igi.setText(record_spilt[2]);

            if(record_spilt[5].equals("Checked In"))
            {
                System.out.println("reached here: "+record_spilt[5]);
                Picasso.with(getApplicationContext()).load(R.drawable.double_tick).into(imgviews_igi);
                txtview_igi.setText("Arrived at Venue");
            }

        }
    public void closeDialog(View view)
    {Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        view.startAnimation(myAnim);
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    }

