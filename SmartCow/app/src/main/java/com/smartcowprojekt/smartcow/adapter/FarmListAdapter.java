package com.smartcowprojekt.smartcow.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import com.smartcowprojekt.smartcow.R;
import com.smartcowprojekt.smartcow.app.AppController;
import com.smartcowprojekt.smartcow.model.Farm;

/**
 * Created by ANDREIS on 2/16/2016.
 */
public class FarmListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Farm> farms;

    public FarmListAdapter(Activity activity, List<Farm> farms) {
        this.activity = activity;
        this.farms = farms;
    }

    @Override
    public int getCount() {
        return farms.size();
    }

    @Override
    public Object getItem(int location) {
        return farms.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.farm_row, null);

        TextView farmName = (TextView) convertView.findViewById(R.id.farmName);
        TextView user = (TextView) convertView.findViewById(R.id.user);

        // getting farm data for the row
        Farm farm = farms.get(position);

        // Farm name
        farmName.setText("Farm name: " + farm.getFarmName());

        //User
        user.setText("Farmer: " + farm.getFarmer());

        return convertView;
    }
}
