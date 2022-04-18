package com.androidpprog2.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements ResponseListener {
    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIManager.authenticateUser(view.getContext(), LoginActivity.this, "armand@gmail.com", "password12");
            }
        });
    }

    @Override
    public void onResponse(String response) {
        //Comprovar dades dels text inputs abans!!!
        try {
            JSONObject jsonObject = new JSONObject(response);
            String token = jsonObject.getString("accessToken");
            APIManager.setToken(token);
        } catch (Exception e) {
            Toast.makeText(this, "ERROR API" + e, Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(VolleyError error) {
        // clean inputs i fer make toast
        Toast.makeText(this, "ERROR " + error, Toast.LENGTH_LONG).show();
    }
}