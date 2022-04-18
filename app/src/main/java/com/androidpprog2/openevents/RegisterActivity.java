package com.androidpprog2.openevents;

import android.content.Context;
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

public class RegisterActivity extends AppCompatActivity implements ResponseListener {
    private EditText usernameText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private EditText emailText;
    private EditText lastNameText;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String lastName;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.register_button_sign_up);

        usernameText = findViewById(R.id.username_input_sign_up);
        emailText = findViewById(R.id.email_input_sign_up);
        passwordText = findViewById(R.id.password_input_sign_up);
        confirmPasswordText = findViewById(R.id.confirm_password_input_sign_up);
        lastNameText = findViewById(R.id.last_name_input_sign_up);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameText.getText().toString();
                password = passwordText.getText().toString();
                confirmPassword = confirmPasswordText.getText().toString();
                email = emailText.getText().toString();
                lastName = lastNameText.getText().toString();

                if (!username.isEmpty() && !email.isEmpty() && !lastName.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
                    if (password.equals(confirmPassword)) {
                        APIManager.postUser(view.getContext(), RegisterActivity.this, username, lastName, password, email, " ");
                    } else {
                        Toast.makeText(view.getContext(), "Passwords don't match.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "One or more empty inputs.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onResponse(String response) {
        Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, "ERROR API " + error, Toast.LENGTH_LONG).show();
    }
}