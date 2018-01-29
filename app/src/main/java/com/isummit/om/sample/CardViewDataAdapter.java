package com.isummit.om.sample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sai_Kameswari on 26-01-2018.
 */

public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {
    public List<String> names;
    public Context context;



    // Provide a suitable constructor (depends on the kind of dataset)
    public CardViewDataAdapter(List names, Context context) {
        this.names = names;
        this.context=context;
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
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.tvtinfo_text.setText(names.get(position));


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return names.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public  class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvtinfo_text;
       // public Button bt1,bt2;
        public ImageView img;
        Context context;
        private TextView tv_gname, tv_desig, tv_profile, tv_company;
        private Button bt_status;
        public ViewHolder(View itemLayoutView, final Context context) {
            super(itemLayoutView);
            this.context=context;
            tvtinfo_text = (TextView) itemLayoutView.findViewById(R.id.info_text);
            img=(ImageView)itemLayoutView.findViewById(R.id.imageView);

            /*itemLayoutView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();
                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){

                        Toast.makeText(v.getContext(), "You clicked " + pos, Toast.LENGTH_SHORT).show();
                       //passActivity();




                    }
                }
            });*/


        }

     /*   public void passActivity()
        {
            System.out.println("Intent being sent");
            Intent testi=new Intent(context,GuestInfoDialogActivity.class);
            context.startActivity(testi);
        }*/

    }

}
