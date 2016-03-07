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
import com.smartcowprojekt.smartcow.model.Cattle;

/**
 * Created by ANDREIS on 2/16/2016.
 */
public class CattleListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Cattle> cattleItems;
   /* ImageLoader imageLoader = AppController.getInstance().getImageLoader();*/

    public CattleListAdapter(Activity activity, List<Cattle> cattleItems) {
        this.activity = activity;
        this.cattleItems = cattleItems;
    }

    @Override
    public int getCount() {
        return cattleItems.size();
    }

    @Override
    public Object getItem(int location) {
        return cattleItems.get(location);
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
            convertView = inflater.inflate(R.layout.cattle_row, null);

        /*if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);*/

        TextView cattleName = (TextView) convertView.findViewById(R.id.cattleName);
        TextView breed = (TextView) convertView.findViewById(R.id.breed);

        // getting cattle data for the row
        Cattle cattle = cattleItems.get(position);

        /*// thumbnail image
        thumbNail.setImageUrl(cattle.getThumbnailUrl(), imageLoader);*/

        // cattle name
        cattleName.setText("Cattle name: " + cattle.getCattleName());

        // breed breed.setText("Breed: " + String.valueOf(m.getRating()));
        breed.setText("Breed: " +cattle.getBreed());

        // iterating a string array list
        /*String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);*/

        return convertView;
    }

}
