package com.smartcowprojekt.smartcow.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartcowprojekt.smartcow.R;
import com.smartcowprojekt.smartcow.model.Displacement;

/**
 * Created by ANDREIS on 2/16/2016.
 */
public class DisplacementListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Displacement> displacements;

    public DisplacementListAdapter(Activity activity, List<Displacement> displacements) {
        this.activity = activity;
        this.displacements = displacements;
    }

    @Override
    public int getCount() {
        return displacements.size();
    }

    @Override
    public Object getItem(int location) {
        return displacements.get(location);
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
            convertView = inflater.inflate(R.layout.displacement_row, null);

        TextView latitude = (TextView) convertView.findViewById(R.id.latitude);
        TextView longitude = (TextView) convertView.findViewById(R.id.longitude);
        TextView speed = (TextView) convertView.findViewById(R.id.speed);
        TextView cattle = (TextView) convertView.findViewById(R.id.cattle);

        // getting displacement data for the row
        Displacement displacement = displacements.get(position);

        // latitude
        latitude.setText("Latitude: " +String.valueOf(displacement.getLatitude()));

        //longitude
        longitude.setText("Longitude: " + String.valueOf(displacement.getLongitude()));

        //speed
        speed.setText("Speed: " + String.valueOf(displacement.getSpeed()));

        //cattle
        cattle.setText("Cattle: " + displacement.getCattle());

        return convertView;
    }
}
