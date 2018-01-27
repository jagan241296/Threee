package com.isummit.om.sample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sai_Kameswari on 26-01-2018.
 */

public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {
    public String[] mDataset;



    // Provide a suitable constructor (depends on the kind of dataset)
    public CardViewDataAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CardViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cardview_row, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.tvtinfo_text.setText(mDataset[position].toString());
        //viewHolder.bt1.setText("STATUS");
       // viewHolder.bt1.setText("PROFILE");

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvtinfo_text;
       // public Button bt1,bt2;
        public ImageView img;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tvtinfo_text = (TextView) itemLayoutView.findViewById(R.id.info_text);
          //  bt1=(Button)itemLayoutView.findViewById(R.id.button);
          //  bt2=(Button)itemLayoutView.findViewById(R.id.button2);
            img=(ImageView)itemLayoutView.findViewById(R.id.imageView);

        }
    }

}
