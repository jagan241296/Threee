package com.isummit.om.sample;

/**
 * Created by Sai_Kameswari on 24-01-2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.squareup.picasso.Picasso;

public class TabFragment3 extends Fragment implements View.OnClickListener{
    private CardView cardAlumni;
    private CardView cardSummit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab_fragment_3, container, false);
        cardAlumni = view.findViewById(R.id.cardAlumni);
        cardSummit = view.findViewById(R.id.cardSummit);

        cardAlumni.setOnClickListener(this);
        cardSummit.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.cardAlumni:
            {
                Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
                cardAlumni.startAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent aa = new Intent(getActivity(),ActivityAlumniAgenda.class);
                        startActivity( aa );
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
            break;
            case R.id.cardSummit:
            {

                Animation myAnima = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
                cardSummit.startAnimation(myAnima);

                myAnima.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent aa = new Intent(getActivity(),ActivitySummitAgenda.class);
                        startActivity( aa );
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

        }
    }
}