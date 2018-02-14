package com.isummit.om.sample;

/**
 * Created by Developer on 30-01-2018.
 */


        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.List;

/**
 * Created by Developer on 21-01-2018.
 */


public class CustomGrid extends BaseAdapter {

    private Context mContext;
    private final List<String> web;


    View grid;


    public CustomGrid(Context c, List<String> web) {
        mContext=c;
        this.web = web;
    }

    @Override
    public int getCount() {
        return web.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null) {
            grid=new View(mContext);
            grid = inflater.inflate(R.layout.gridview, null);
        }
        else
        {
            grid= convertView;
        }
        TextView textView =  grid.findViewById(R.id.grid_text);
        textView.setText(web.get(position));
        return grid;
    }
}
