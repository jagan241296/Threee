package com.isummit.om.sample;

/**
 * Created by Sai_Kameswari on 24-01-2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TabFragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab_fragment_3, container, false);
        CardView cardAlumni = (CardView)view.findViewById(R.id.cardAlumni);
        CardView cardSummit = (CardView)view.findViewById(R.id.cardSummit);


        cardAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aa = new Intent(getActivity(),ActivityAlumniAgenda.class);
                startActivity( aa );
            }
        });

        cardSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aa = new Intent(getActivity(),ActivitySummitAgenda.class);
                startActivity( aa );
            }
        });
        return view;


    }

}