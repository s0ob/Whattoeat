package com.example.user.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.user.project.R.layout.itemview;

/**
 * Created by User on 4/3/2017.
 */

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<World> worldlist = null;
    private ArrayList<World> arraylist;

    public ListViewAdapter(Context context, List<World> worldlist) {
        mContext = context;
        this.worldlist = worldlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(worldlist);
    }

    public class ViewHolder {
        TextView name;
        TextView address;
    }

    @Override
    public int getCount() {
        return worldlist.size();
    }

    @Override
    public World getItem(int position) {
        return worldlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(itemview, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.textView);
            holder.address = (TextView) view.findViewById(R.id.textView2);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(worldlist.get(position).getname());
        holder.address.setText(worldlist.get(position).getaddress());

        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, itemview.class);
                // Pass all data rank
                intent.putExtra("name",(worldlist.get(position).getname()));
                // Pass all data country
                intent.putExtra("address",(worldlist.get(position).getaddress()));
                // Pass all data population
                // Pass all data flag
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        worldlist.clear();
        if (charText.length() == 0) {
            worldlist.addAll(arraylist);
        }
        else
        {
            for (World wp : arraylist)
            {
                if (wp.getname().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    worldlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}