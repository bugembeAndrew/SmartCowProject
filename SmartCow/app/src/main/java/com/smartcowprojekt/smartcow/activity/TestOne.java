package com.smartcowprojekt.smartcow.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.smartcowprojekt.smartcow.R;

public class TestOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_one);
    }

    /* Initiating Menu XML file (menu.xml)*/
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menu_save:

                return true;

            case R.id.menu_edit:

                return true;

            case R.id.menu_search:

                return true;

            case R.id.menu_delete:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
