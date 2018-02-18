package com.isummit.om.developers;

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
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sai_Kameswari on 14-02-2018.
 */

public class GuestCategoryAdapter extends RecyclerView.Adapter<GuestCategoryAdapter.ViewHolder> {

    public List<String> guest_cat;
    public List<String> guest_count;
    public Context context;



    // Provide a suitable constructor (depends on the kind of dataset)
    public GuestCategoryAdapter(List guest_cat,List guest_count, Context context) {
        this.guest_cat = guest_cat;
        this.guest_count = guest_count;
        this.context = context;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public GuestCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_guest_card_item, null);

        // create ViewHolder

        GuestCategoryAdapter.ViewHolder viewHolder = new GuestCategoryAdapter.ViewHolder(itemLayoutView,context);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(GuestCategoryAdapter.ViewHolder viewHolder, int position) {

        viewHolder.tv_guest_cat.setText(guest_cat.get(position));
        viewHolder.tv_guest_count.setText(guest_count.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return guest_count.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public  class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_guest_cat, tv_guest_count;

        Context context;
        public ViewHolder(final View itemLayoutView, final Context context) {
            super(itemLayoutView);
            this.context=context;
            tv_guest_cat = itemLayoutView.findViewById(R.id.tv_guest_cat);
            tv_guest_count = itemLayoutView.findViewById(R.id.tv_guest_count);

            itemLayoutView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    final int pos = getAdapterPosition();
                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){

                        Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                        itemLayoutView.startAnimation(myAnim);
                        myAnim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                passActivity(guest_cat.get(pos));

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
            Intent testi=new Intent(context,GuestListActivity.class);
            testi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            testi.putExtra("category",data);
            context.startActivity(testi);
        }
    }
}
