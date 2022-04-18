package com.androidpprog2.openevents;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        Button registerButton = findViewById(R.id.edit_profile_button);

        usernameText = findViewById(R.id.name_edit);
        emailText = findViewById(R.id.email_edit);
        passwordText = findViewById(R.id.password_edit);
        confirmPasswordText = findViewById(R.id.image_edit);
        lastNameText = findViewById(R.id.lastname_edit);

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
                        Toast.makeText(view.getContext(), R.string.toast_error_password_match, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), R.string.toast_empty, Toast.LENGTH_LONG).show();
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
        Toast.makeText(this, R.string.toast_api_error_register, Toast.LENGTH_LONG).show();
    }
}