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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.smartcowprojekt.smartcow.R;
import com.smartcowprojekt.smartcow.app.AppConfig;
import com.smartcowprojekt.smartcow.app.AppController;
import com.smartcowprojekt.smartcow.util.SQLiteHandler;
import com.smartcowprojekt.smartcow.util.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Cattle extends AppCompatActivity {

    private static final String TAG = Cattle.class.getSimpleName();
    private static int SOCKET_TIMEOUT = 30000;
    private ProgressDialog pDialog;
    private EditText name;
    private EditText weight;
    private EditText sex;
    private EditText dob;
    private EditText breed;
    private EditText milk;
    private EditText offSpring;
    private EditText farm;
    private EditText created;
    private EditText updated;
    private SQLiteHandler db;
    private SessionManager session;
    private Button logout;
    private TextView welcomeTag;
    private String firstName;
    private String lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cattle);

        //final String cattle_id = String.valueOf(getIntent().getExtras().getInt("cattle_id"));

       /* pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();*/

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

        name = (EditText) findViewById(R.id.cattleName);
        weight = (EditText) findViewById(R.id.weight);
        sex = (EditText) findViewById(R.id.sex);
        dob = (EditText) findViewById(R.id.dob);
        breed = (EditText) findViewById(R.id.breed);
        milk = (EditText) findViewById(R.id.milk);
        offSpring = (EditText) findViewById(R.id.offSpring);
        farm = (EditText) findViewById(R.id.farm);
        created = (EditText) findViewById(R.id.created);
        updated = (EditText) findViewById(R.id.updated);

        name.setText(getIntent().getExtras().getString("cattleName"));
        weight.setText(getIntent().getExtras().getString("weight"));
        sex.setText(getIntent().getExtras().getString("sex"));
        dob.setText(getIntent().getExtras().getString("dateOfBirth"));
        breed.setText(getIntent().getExtras().getString("breed"));
        milk.setText(Double.toString(getIntent().getExtras().getDouble("litresOfMilk")));
        offSpring.setText(Integer.toString(getIntent().getExtras().getInt("offSpring")));
        farm.setText(Integer.toString(getIntent().getExtras().getInt("farm")));
        created.setText(getIntent().getExtras().getString("created_at"));
        updated.setText(getIntent().getExtras().getString("updated_at"));

        // Creating volley request obj
        /*StringRequest cattleReq = new StringRequest(Request.Method.GET, AppConfig.CATTLE_BY_ID_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        hidePDialog();

                        // Parsing json
                        try {
                            JSONObject objekt = new JSONObject(response);
                            boolean error = objekt.getBoolean("error");
                            if(!error) {
                                JSONArray cattleArray = objekt.getJSONArray("cattle");
                                for(int j = 0; j < cattleArray.length(); j++) {
                                    JSONObject obj = cattleArray.getJSONObject(j);
                                    name.setText(obj.getString("cattleName"));
                                    weight.setText(obj.getString("weight"));
                                    sex.setText(obj.getString("sex"));
                                    dob.setText(obj.getString("dateOfBirth"));
                                    breed.setText(obj.getString("breed"));
                                    milk.setText(Double.toString(obj.getDouble("litresOfMilk")));
                                    offSpring.setText(Integer.toString(obj.getInt("offSpring")));
                                    farm.setText(Integer.toString(obj.getInt("farm")));
                                    created.setText(obj.getString("created_at"));
                                    updated.setText(obj.getString("updated_at"));
                                }
                            } else {
                                String errorMsg = objekt.getString("error_msg");
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        }){

            @Override
            protected java.util.Map<String, String> getParams() {
                // Posting parameters to sign in url
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put("cattle_id", cattle_id);
                return params;
            }

        };

        cattleReq.setRetryPolicy(new DefaultRetryPolicy(
                SOCKET_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(cattleReq);*/
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
        Intent intent = new Intent(Cattle.this, Login.class);
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
