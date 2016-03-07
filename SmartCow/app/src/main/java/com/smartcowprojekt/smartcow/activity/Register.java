package com.smartcowprojekt.smartcow.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartcowprojekt.smartcow.R;
import com.smartcowprojekt.smartcow.app.AppConfig;
import com.smartcowprojekt.smartcow.app.AppController;
import com.smartcowprojekt.smartcow.util.SQLiteHandler;
import com.smartcowprojekt.smartcow.util.SessionManager;

public class Register extends Activity implements OnItemSelectedListener {

    private static final String TAG = Register.class.getSimpleName();

    // Socket timeout
    private static int SOCKET_TIMEOUT = 30000;

    private EditText firstName;
    private EditText lastName;
    private Spinner sex;
    private EditText emailAddress;
    private EditText tel;
    private Spinner category;
    private EditText pass;
    private EditText confirmPassword;
    private Button signUp;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private String sexItem = null;
    private String categoryItem = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        firstName = (EditText) findViewById(R.id.fName);
        lastName = (EditText) findViewById(R.id.lName);
        sex = (Spinner) findViewById(R.id.gender);
        emailAddress = (EditText) findViewById(R.id.email);
        tel = (EditText) findViewById(R.id.telephone);
        category = (Spinner) findViewById(R.id.category);
        pass = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        signUp = (Button) findViewById(R.id.sign_up);

        // Spinner click listener
        sex.setOnItemSelectedListener(this);
        category.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> sexes = new ArrayList<String>();
        sexes.add("Female");
        sexes.add("Male");
        sexes.add("Other");

        List<String> categories = new ArrayList<String>();
        categories.add("Administrator");
        categories.add("Farmer");
        categories.add("Veterinary Doctor");

        // Creating adapter for spinner
        ArrayAdapter<String> sexDataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, sexes);
        ArrayAdapter<String> categoryDataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        sexDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sex.setAdapter(sexDataAdapter);
        category.setAdapter(categoryDataAdapter);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take user to Main activity
            Intent intent = new Intent(Register.this,
                    Main.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String first_name = firstName.getText().toString().trim();
                String last_name = lastName.getText().toString().trim();
                String sex = sexItem.trim();
                String email = emailAddress.getText().toString().trim();
                String telephone = tel.getText().toString().trim();
                String category = categoryItem.trim();
                String password = pass.getText().toString().trim();
                String confirm = confirmPassword.getText().toString().trim();

                if (!first_name.isEmpty() && !last_name.isEmpty() && !sex.isEmpty()
                        && !email.isEmpty() && !telephone.isEmpty() && !category.isEmpty()
                        && !password.isEmpty() && !confirm.isEmpty()) {
                    if(password.equals(confirm)){
                        registerUser(first_name, last_name, sex, email, telephone, category,
                                password);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Passwords do not match!", Toast.LENGTH_LONG)
                                .show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

    }

    /**
     * Function to store user in MySQL database will post params(tag, first_name, last_name, sex,
     * email, telephone, category, password) to register url
     * */
    private void registerUser(final String first_name, final String last_name, final String sex,
                              final String email, final String telephone, final String category,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_sign_up";

        pDialog.setMessage("Signing Up ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.REGISTER_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Sign Up Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in SQLite
                        JSONArray userArray = jObj.getJSONArray("user");
                        for (int i = 0; i < userArray.length(); i++) {
                            // Now store the user in SQLite
                            JSONObject user = userArray.getJSONObject(i);
                            String unique_id = user.getString("unique_id");
                            String first_name = user.getString("first_name");
                            String last_name = user.getString("last_name");
                            String sex = user.getString("sex");
                            String email = user.getString("email");
                            String telephone = user.getString("telephone");
                            String category = user.getString("category");
                            String password = user.getString("encrypted_password");
                            String created_at = user.getString("created_at");
                            // Inserting row in users table
                            db.addUser(first_name, last_name, sex, email, telephone, category,
                                    password, unique_id, created_at);
                        }
                        Toast.makeText(getApplicationContext(), "You Have Successfully " +
                                "Signed Up! Try Signing In now!", Toast.LENGTH_LONG).show();

                        // Launch Sign In activity
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred during sign up. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
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
                Log.e(TAG, "Sign Up Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("first_name", first_name);
                params.put("last_name", last_name);
                params.put("sex", sex);
                params.put("email", email);
                params.put("telephone", telephone);
                params.put("category", category);
                params.put("password", password);

                return params;
            }

        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(
                SOCKET_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.gender:
                this.sexItem = parent.getItemAtPosition(position).toString();
                break;
            case R.id.category:
                this.categoryItem = parent.getItemAtPosition(position).toString();
                break;
            default:
                Toast.makeText(parent.getContext(), "Error selecting item!",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
