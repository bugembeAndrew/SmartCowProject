package com.smartcowprojekt.smartcow.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.smartcowprojekt.smartcow.R;
import com.smartcowprojekt.smartcow.app.AppConfig;
import com.smartcowprojekt.smartcow.app.AppController;
import com.smartcowprojekt.smartcow.util.SQLiteHandler;
import com.smartcowprojekt.smartcow.util.SessionManager;

public class Login extends Activity {

    private static final String TAG = Login.class.getSimpleName();

    // Socket timeout
    private static int SOCKET_TIMEOUT = 30000;

    private Button signIn;
    private Button signUpLink;
    private EditText email;
    private EditText password;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.sign_in);
        signUpLink = (Button) findViewById(R.id.link_to_sign_up);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take user to Main activity
            Intent intent = new Intent(Login.this, Main.class);
            startActivity(intent);
            finish();
        }

        // Sign In button Click Event
        signIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String emailInput = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();

                // Check for empty data in the form
                if (!emailInput.isEmpty() && !passwordInput.isEmpty()) {
                    // sign in user
                    checkLogin(emailInput, passwordInput);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

        // Link to Sign Up
        signUpLink.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        Register.class);
                startActivity(i);
                finish();
            }
        });

    }

    /**
     * function to verify sign in details in mysql db
     * */
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_sign_in";

        pDialog.setMessage("Signing in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.LOGIN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Sign In Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);
                        JSONArray userArray = jObj.getJSONArray("user");
                        for (int i = 0; i < userArray.length(); i++) {
                            // Now store the user in SQLite
                            JSONObject user = userArray.getJSONObject(i);
                            String unique_id = user.getString("unique_id");
                            String first_name = user.getString("first_name");
                            String last_name =  user.getString("last_name");
                            String sex =  user.getString("sex");
                            String email =  user.getString("email");
                            String telephone =  user.getString("telephone");
                            String category =  user.getString("category");
                            String password =  user.getString("encrypted_password");
                            String created_at = user.getString("created_at");
                            // Inserting row in users table
                            db.addUser(first_name, last_name, sex, email, telephone, category,
                                    password, unique_id, created_at);
                        }
                        // Launch Main activity
                        Intent intent = new Intent(Login.this,
                                Main.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in sign in. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Sign in Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to sign in url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
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
}
