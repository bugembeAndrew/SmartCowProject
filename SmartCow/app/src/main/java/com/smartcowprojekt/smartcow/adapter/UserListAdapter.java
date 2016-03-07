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
import com.smartcowprojekt.smartcow.model.User;

/**
 * Created by ANDREIS on 2/16/2016.
 */
public class UserListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<User> users;

    public UserListAdapter(Activity activity, List<User> users) {
        this.activity = activity;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int location) {
        return users.get(location);
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
            convertView = inflater.inflate(R.layout.user_row, null);

        TextView userName = (TextView) convertView.findViewById(R.id.userName);
        TextView telephone = (TextView) convertView.findViewById(R.id.telephone);

        // getting user data for the row
        User user = users.get(position);

        // user name
        userName.setText("Username: " + user.getFirst_name()+" "+user.getLast_name());

        //telephone
        telephone.setText("Telephone: " + user.getTelephone());

        return convertView;
    }
}
