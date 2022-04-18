package com.androidpprog2.openevents;

import android.content.Context;
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

public class RegisterActivity extends AppCompatActivity implements ResponseListener {
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = findViewById(R.id.register_button_sign_up);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Comprovar dades dels text inputs abans!!!
                APIManager.postUser(view.getContext(), RegisterActivity.this, "Pol", "YEPA", "password12", "armand@gmail.com", "https://i.imgur.com/ghy8Xx1.png");
            }
        });
    }

    @Override
    public void onResponse(String response) {
        // save token
        Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(VolleyError error) {
        // clean inputs i fer make toast
        Toast.makeText(this, "ERROR API " + error, Toast.LENGTH_LONG).show();
    }
}