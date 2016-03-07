package com.smartcowprojekt.smartcow.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smartcowprojekt.smartcow.R;

public class Dashboard extends Fragment {
    public Dashboard() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Dashboard buttons
        Button maps = (Button) rootView.findViewById(R.id.btn_maps);
        Button cattle = (Button) rootView.findViewById(R.id.btn_cattle);
        Button farms = (Button) rootView.findViewById(R.id.btn_farm);
        Button users = (Button) rootView.findViewById(R.id.btn_user);
        Button info = (Button) rootView.findViewById(R.id.btn_info);
        Button settings = (Button) rootView.findViewById(R.id.btn_settings);

        // Listening to Maps button click
        maps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Maps Screen
                Intent i = new Intent(getActivity(), Map.class);
                startActivity(i);
            }
        });
        // Listening Cattle button click
        cattle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Cattle Screen
                Intent i = new Intent(getActivity(), CattleList.class);
                startActivity(i);
            }
        });
        // Listening Farms button click
        farms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Farms Screen
                Intent i = new Intent(getActivity(), FarmList.class);
                startActivity(i);
            }
        });
        // Listening to Users button click
        users.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Users Screen
                Intent i = new Intent(getActivity(), UserList.class);
                startActivity(i);
            }
        });
        // Listening to Info button click
        info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Info Screen
                Intent i = new Intent(getActivity(), DisplacementList.class);
                startActivity(i);
            }
        });
        // Listening to Settings button click
        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Settings Screen
                Intent i = new Intent(getActivity(), Setting.class);
                startActivity(i);
            }
        });

        return rootView;
    }
}
