package com.isummit.om.sample;

/**
 * Created by Sai_Kameswari on 24-01-2018.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class TabFragment1 extends Fragment {
    private TextView txtTimerDay, txtTimerHour, txtTimerMinute, txtTimerSecond;
    private TextView tvEvent;
    private Handler handler;
    private Runnable runnable;
    private String val;
    private String prefName = "userNamePref";
    private String EVENT_TIME="event_time";
    private Context context;
    CardView click;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.tab_fragment_1, container, false);

        click=rootView.findViewById(R.id.live);

        txtTimerDay = rootView.findViewById(R.id.txtTimerDay);
        txtTimerHour = rootView.findViewById(R.id.txtTimerHour);
        txtTimerMinute = rootView.findViewById(R.id.txtTimerMinute);
        txtTimerSecond = rootView.findViewById(R.id.txtTimerSecond);
        tvEvent = rootView.findViewById(R.id.tvhappyevent);


        SharedPreferences prefs = this.getContext().getSharedPreferences(prefName, MODE_PRIVATE);
        val = prefs.getString(EVENT_TIME, "");
        System.out.println("Vlue: "+val);

            countDownStart();
       return rootView;
    }

    public void countDownStart() {
        //Variables that store user credentials
        if(val.equals(""))
        {
            val="23-02-2018-18-06-00";
        }
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "dd-MM-yyyy-hh-mm-ss");
                    // Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse(val);

                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        txtTimerDay.setText("" + String.format("%02d", days));
                        txtTimerHour.setText("" + String.format("%02d", hours));
                        txtTimerMinute.setText(""
                                + String.format("%02d", minutes));
                        txtTimerSecond.setText(""
                                + String.format("%02d", seconds));
                    } else {
                        tvEvent.setVisibility(View.VISIBLE);
                        tvEvent.setText("The event started!Click on Go Live");
                        textViewGone();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

    public void textViewGone() {
        Toast.makeText(getContext(),"Click on Go Live",Toast.LENGTH_SHORT).show();
    }
}