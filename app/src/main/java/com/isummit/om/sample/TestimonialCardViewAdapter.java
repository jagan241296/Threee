package com.isummit.om.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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



    // Provide a suitable constructor (depends on the kind of dataset)
    public TestimonialCardViewAdapter(List testimonials, List testimonials_date, List testimonials_username) {
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

        TestimonialCardViewAdapter.ViewHolder viewHolder = new TestimonialCardViewAdapter.ViewHolder(itemLayoutView);
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

        public TextView tv_user;
        public TextView tv_message;
        public TextView tv_date;
        // public Button bt1,bt2;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tv_message = (TextView) itemLayoutView.findViewById(R.id.msg);
            tv_user = (TextView) itemLayoutView.findViewById(R.id.user);
            tv_date = (TextView) itemLayoutView.findViewById(R.id.date);



        }
    }

}
