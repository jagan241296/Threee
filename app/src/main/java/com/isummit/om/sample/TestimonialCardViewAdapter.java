package com.isummit.om.sample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sai_Kameswari on 29-01-2018.
 */

public class TestimonialCardViewAdapter extends RecyclerView.Adapter<TestimonialCardViewAdapter.ViewHolder> {

    public List<String> testimonials;
    public List<String> testimonials_date;
    public List<String> testimonials_username;
    private Context context;



    // Provide a suitable constructor (depends on the kind of dataset)
    public TestimonialCardViewAdapter(Context context, List testimonials, List testimonials_date, List testimonials_username) {
        this.context=context;
        this.testimonials = testimonials;
        this.testimonials_date=testimonials_date;
        this.testimonials_username=testimonials_username;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TestimonialCardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.listview_items, null);

        // create ViewHolder

        TestimonialCardViewAdapter.ViewHolder viewHolder = new TestimonialCardViewAdapter.ViewHolder(context,itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TestimonialCardViewAdapter.ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.tv_user.setText(testimonials_username.get(position));
        viewHolder.tv_message.setText(testimonials.get(position));
        viewHolder.tv_date.setText(testimonials_date.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return testimonials.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView tv_card;
        public TextView tv_user;
        public TextView tv_message;
        public TextView tv_date;
        private Context context;
        // public Button bt1,bt2;


        public ViewHolder(final Context context, View itemLayoutView) {
            super(itemLayoutView);
            this.context = context;
            tv_card = itemLayoutView.findViewById(R.id.testimonial_card);
            tv_message =  itemLayoutView.findViewById(R.id.msg);
            tv_user =  itemLayoutView.findViewById(R.id.user);
            tv_date =  itemLayoutView.findViewById(R.id.date);

            tv_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                    v.startAnimation(myAnim);
                }
            });



        }
    }

}
