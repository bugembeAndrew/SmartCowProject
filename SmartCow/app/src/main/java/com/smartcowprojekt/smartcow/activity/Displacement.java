package com.smartcowprojekt.smartcow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smartcowprojekt.smartcow.R;
import com.smartcowprojekt.smartcow.util.SQLiteHandler;
import com.smartcowprojekt.smartcow.util.SessionManager;

import java.util.HashMap;

public class Displacement extends AppCompatActivity {

    private SQLiteHandler db;
    private SessionManager session;
    private Button logout;
    private TextView welcomeTag;
    private String firstName;
    private String lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displacement);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
        // session manager
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }
        // Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();
        firstName = user.get("first_name");
        lastName = user.get("last_name");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from SQLite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(Displacement.this, Login.class);
        startActivity(intent);
        finish();
    }

    /* Initiating Menu XML file (menu.xml)*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        /*MenuItem searchItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.onActionViewExpanded();
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //execute query here
                searchView.clearFocus();//work around issues with some emulators and keyboards
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logoutActionView = menu.findItem(R.id.logoutActionButton);
        MenuItem welcomeActionView = menu.findItem(R.id.welcomeAction);

        View logoutView = MenuItemCompat.getActionView(logoutActionView);
        View welcomeView = MenuItemCompat.getActionView(welcomeActionView);

        //welcome tag
        welcomeTag = (TextView) welcomeView.findViewById(R.id.welcomeTag);
        welcomeTag.setText(""+firstName+" "+lastName);

        logout = (Button) logoutView.findViewById(R.id.btn_logout);
        // Listening to logout button click
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Signing out
                logoutUser();
            }
        });
        return super.onPrepareOptionsMenu(menu);
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
