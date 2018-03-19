package com.isummit.om.developers;

import android.content.Context;
import android.support.v7.widget.CardView;
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
 * Created by Sai_Kameswari on 17-02-2018.
 */

public class DeveloperCardViewAdapter extends RecyclerView.Adapter<DeveloperCardViewAdapter.ViewHolder> {
    public List<String> developers_name, developers_clas, developers_image, developers_team;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public DeveloperCardViewAdapter(Context context, List developers_name, List developers_clas, List developers_image, List developers_team) {
        this.context=context;
        this.developers_name = developers_name;
        this.developers_clas = developers_clas;
        this.developers_image = developers_image;
        this.developers_team = developers_team;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DeveloperCardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.developer_card_view, null);

        // create ViewHolder

        DeveloperCardViewAdapter.ViewHolder viewHolder = new DeveloperCardViewAdapter.ViewHolder(context,itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DeveloperCardViewAdapter.ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.name.setText(developers_name.get(position));
        viewHolder.clas.setText(developers_clas.get(position));
        viewHolder.team.setText(developers_team.get(position));
        Picasso.with(context).load(developers_image.get(position)).into(viewHolder.our_pic);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return developers_name.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView outer_card;
        public TextView clas, name, team;
        public ImageView our_pic;

        private Context context;
        // public Button bt1,bt2;


        public ViewHolder(final Context context, View itemLayoutView) {
            super(itemLayoutView);
            this.context = context;
            outer_card = itemLayoutView.findViewById(R.id.outer_card);
            name =  itemLayoutView.findViewById(R.id.name);
            clas =  itemLayoutView.findViewById(R.id.clas);
            our_pic =  itemLayoutView.findViewById(R.id.our_pic);
            team = itemLayoutView.findViewById(R.id.team);

            outer_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);
                    v.startAnimation(myAnim);
                }
            });

        }
    }
}
