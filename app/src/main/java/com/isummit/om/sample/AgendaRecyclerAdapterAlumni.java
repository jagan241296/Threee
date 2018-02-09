package com.isummit.om.sample;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sai_Kameswari on 08-02-2018.
 */

public class AgendaRecyclerAdapterAlumni extends RecyclerView.Adapter<AgendaRecyclerAdapterAlumni.ViewHolder> {

    public List<String> agenda_time;
    public List<String> agenda_msg;
    private Context context;



    // Provide a suitable constructor (depends on the kind of dataset)
    public AgendaRecyclerAdapterAlumni(Context context, List agenda_time, List agenda_msg) {
        this.context=context;
        this.agenda_time = agenda_time;
        this.agenda_msg = agenda_msg;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AgendaRecyclerAdapterAlumni.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.agenda_item, null);

        // create ViewHolder

        AgendaRecyclerAdapterAlumni.ViewHolder viewHolder = new AgendaRecyclerAdapterAlumni.ViewHolder(context,itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AgendaRecyclerAdapterAlumni.ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

       viewHolder.agenda_time.setText(agenda_time.get(position));
        viewHolder.agenda_msg.setText(agenda_msg.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return agenda_time.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public TextView agenda_msg;
        public TextView agenda_time;
        // public Button bt1,bt2;

        public ViewHolder(final Context context, View itemLayoutView) {
            super(itemLayoutView);
            cardView=itemLayoutView.findViewById(R.id.agenda_card);
            agenda_time =  itemLayoutView.findViewById(R.id.agenda_time);
            agenda_msg =  itemLayoutView.findViewById(R.id.agenda_msg);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                    v.startAnimation(myAnim);
                }
            });
        }
    }
}
