package com.androidpprog2.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements ResponseListener {
    private Button loginButton;
    private Button registerButton;
    private EditText emailText;
    private EditText passwordText;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        emailText = findViewById(R.id.username_input);
        passwordText = findViewById(R.id.password_input);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    APIManager.authenticateUser(view.getContext(), LoginActivity.this, email, password);
                } else {
                    Toast.makeText(view.getContext(), R.string.toast_empty, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onResponse(String response) {
        if (APIManager.isTokenNull()) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String token = jsonObject.getString("accessToken");
                APIManager.setToken(token);
                APIManager.setEmailAndGetId(this, this, email);
            } catch (Exception e) {
                Toast.makeText(this, R.string.error_parsing_json_token, Toast.LENGTH_LONG).show();
            }
        } else {
            try {
                JSONArray jsonArray = new JSONArray(response);
                int id = jsonArray.getJSONObject(0).getInt("id");
                APIManager.setId(id);
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, R.string.error_parsing_json_id, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error_login, Toast.LENGTH_LONG).show();
    }
}