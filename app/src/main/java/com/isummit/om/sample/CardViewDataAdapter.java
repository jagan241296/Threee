package com.isummit.om.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sai_Kameswari on 26-01-2018.
 */

public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {
    public List<String> names;
    public List<String> company;
    public List<String> record;
    public List<String> names_url;
    public Context context;



    // Provide a suitable constructor (depends on the kind of dataset)
    public CardViewDataAdapter(List names,List company, List record,List names_url, Context context) {
        this.names = names;
        this.company = company;
        this.context = context;
        this.record = record;
        this.names_url=names_url;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cardview_row, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView,context);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {


        String url=names_url.get(position).toString();

        Picasso.with(context).load(url).into(viewHolder.img);
        viewHolder.tvtinfo_text.setText(names.get(position));
        viewHolder.tvcompany_text.setText("("+company.get(position)+")");

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return names.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public  class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvtinfo_text;
        public TextView tvcompany_text;
       // public Button bt1,bt2;
        public ImageView img;
        Context context;
        public ViewHolder(final View itemLayoutView, final Context context) {
            super(itemLayoutView);
            this.context=context;
            tvtinfo_text = itemLayoutView.findViewById(R.id.info_text);
            tvcompany_text = itemLayoutView.findViewById(R.id.tv_company);
            img= itemLayoutView.findViewById(R.id.imageView);

            itemLayoutView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();
                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){

                       final String data=record.get(pos);
                        Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                        itemLayoutView.startAnimation(myAnim);
                        myAnim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                passActivity(data);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    }
                }
            });
        }

        public void passActivity(String data)
        {
            Bundle args = new Bundle();
            Intent testi=new Intent(context,GuestInfoDialogActivity.class);
            testi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            testi.putExtra("record",data);
            context.startActivity(testi);
        }
    }
}
