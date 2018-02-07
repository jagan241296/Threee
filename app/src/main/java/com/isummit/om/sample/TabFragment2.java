package com.isummit.om.sample;

/**
 * Created by Sai_Kameswari on 24-01-2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class TabFragment2 extends Fragment implements View.OnClickListener{
    Button igi,indira,icem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab_fragment_2, container, false);
        igi=view.findViewById(R.id.igi);
        indira=view.findViewById(R.id.indira);
        icem=view.findViewById(R.id.icem);

        igi.setOnClickListener(this);
        indira.setOnClickListener(this);
        icem.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.igi:
            {
                Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
                igi.startAnimation(myAnim);
            }
            break;
            case R.id.indira:
            {
                Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
                indira.startAnimation(myAnim);
            }
            case R.id.icem:
            {
                Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
                icem.startAnimation(myAnim);
            }
        }
    }
}