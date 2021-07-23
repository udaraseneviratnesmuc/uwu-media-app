package com.uwu.media.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.uwu.media.app.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editTextEmail = findViewById(R.id.editText_email);
        EditText editTextPwd = findViewById(R.id.editTextTextPassword);
        Button buttonSignIn = findViewById(R.id.button_signin);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(editTextEmail.getText().toString(), editTextPwd.getText().toString());
            }
        });
    }

    private void login(String email, String pwd){
        Log.d("LOGIN", "onClick: email " + email);
        Log.d("LOGIN", "onClick: pwd " + pwd);

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String url ="http://192.168.0.103:8081/login";

        JSONObject credentials = new JSONObject();
        try {
            credentials.put("email", email);
            credentials.put("password", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest loginRequest = new JsonObjectRequest(url, credentials,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Tag", "Response is: "+ response);
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(LoginActivity.this, "Login Failed", duration);
                toast.show();
            }
        });

        queue.add(loginRequest);
    }
}