package com.smartcowprojekt.smartcow.activity;

import android.app.Activity;
import android.os.Bundle;

import com.smartcowprojekt.smartcow.R;

public class UserList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_list);
    }
}
