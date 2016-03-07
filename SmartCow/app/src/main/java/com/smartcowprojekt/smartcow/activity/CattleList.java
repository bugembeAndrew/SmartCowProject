package com.smartcowprojekt.smartcow.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.smartcowprojekt.smartcow.R;
import com.smartcowprojekt.smartcow.adapter.CattleListAdapter;
import com.smartcowprojekt.smartcow.app.AppConfig;
import com.smartcowprojekt.smartcow.app.AppController;
import com.smartcowprojekt.smartcow.model.Cattle;
import com.smartcowprojekt.smartcow.util.SQLiteHandler;
import com.smartcowprojekt.smartcow.util.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CattleList extends AppCompatActivity {

    // Log tag
    private static final String TAG = CattleList.class.getSimpleName();

    // Socket timeout
    private static int SOCKET_TIMEOUT = 30000;

    private ProgressDialog pDialog;
    private List<Cattle> cattleList = new ArrayList<Cattle>();
    private ListView listView;
    private CattleListAdapter adapter;
    private SQLiteHandler db;
    private SessionManager session;
    private Button logout;
    private TextView welcomeTag;
    private String firstName;
    private String lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cattle_list);

        listView = (ListView) findViewById(R.id.cattleList);
        adapter = new CattleListAdapter(this, cattleList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

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

        // Creating volley request obj
        StringRequest cattleReq = new StringRequest(AppConfig.ALL_CATTLE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        hidePDialog();

                        // Parsing json
                        try {
                            JSONObject objekt = new JSONObject(response);
                            JSONArray cattleArray = objekt.getJSONArray("cattle");
                            for(int j = 0; j < cattleArray.length(); j++){
                                JSONObject obj = cattleArray.getJSONObject(j);
                                Cattle cattle =
                                        new Cattle(obj.getInt("cattle_id"),
                                                obj.getString("cattleName"),
                                                obj.getString("weight"),
                                                obj.getString("sex"),
                                                obj.getString("dateOfBirth"),
                                                obj.getString("breed"),
                                                obj.getDouble("litresOfMilk"),
                                                obj.getInt("offSpring"),
                                                obj.getInt("farm"),
                                                obj.getString("created_at"),
                                                obj.getString("updated_at"));
                                cattleList.add(cattle);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        cattleReq.setRetryPolicy(new DefaultRetryPolicy(
                SOCKET_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(cattleReq);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cattle item = (Cattle) adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(),
                        com.smartcowprojekt.smartcow.activity.Cattle.class);
                intent.putExtra("cattle_id", item.getCattle_id());
                intent.putExtra("cattleName", item.getCattleName());
                intent.putExtra("weight", item.getWeight());
                intent.putExtra("sex", item.getSex());
                intent.putExtra("dateOfBirth", item.getDateOfBirth());
                intent.putExtra("breed", item.getBreed());
                intent.putExtra("litresOfMilk", item.getLitresOfMilk());
                intent.putExtra("offSpring", item.getOffSpring());
                intent.putExtra("farm", item.getFarm());
                intent.putExtra("created_at", item.getCreated_at());
                intent.putExtra("updated_at", item.getUpdated_at());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from SQLite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(CattleList.this, Login.class);
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
