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

/**
 * Created by Developer on 21-01-2018.
 */


public class CustomGrid extends BaseAdapter {

    private Context mContext;
    private final String[] web;

    public final int[] imageid;
    View grid;


    public CustomGrid(Context c, String[] web,int[] imageid) {
        mContext=c;
        this.web = web;

        this.imageid = imageid;
    }

    @Override
    public int getCount() {
        return web.length;
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
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.gridimage);
            textView.setText(web[position]);
            //imageView.setImageResource(imageid[position]);
        }
        else
        {
            grid= convertView;
        }
        return grid;
    }
}
