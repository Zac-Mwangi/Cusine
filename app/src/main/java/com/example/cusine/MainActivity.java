package com.example.cusine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText username,password;
Button loginBtn;

String login_url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {


           Login();
        });
    }
    public void Login(){
        String stringUser = username.getText().toString();
        String strpassword = password.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, login_url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //dismiss loader


                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")) {
                    } else {


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            Toast.makeText(this, "Network Err", Toast.LENGTH_SHORT).show();
            //Display error message whenever an error occurs
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("username",stringUser);
                params.put("password", strpassword);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}