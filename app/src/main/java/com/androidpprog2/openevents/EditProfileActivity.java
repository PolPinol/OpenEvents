package com.androidpprog2.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity implements ResponseListener {
    private EditText nameText;
    private EditText emailText;
    private EditText lastNameText;
    private EditText passwordText;
    private EditText imageText;
    private String name;
    private String email;
    private String lastName;
    private String password;
    private String image;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();

        nameText = findViewById(R.id.name_edit);
        emailText = findViewById(R.id.email_edit);
        lastNameText = findViewById(R.id.lastname_edit);
        passwordText = findViewById(R.id.password_edit);
        imageText = findViewById(R.id.image_edit);

        editButton = findViewById(R.id.button_complete_edit);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameText.getText().toString();
                password = passwordText.getText().toString();
                email = emailText.getText().toString();
                lastName = lastNameText.getText().toString();
                image = imageText.getText().toString();

                Map<String, String> map = new HashMap<>();

                if (!name.isEmpty()) {
                    map.put("name", name);
                }

                if (!password.isEmpty()) {
                    map.put("password", password);
                }

                if (!lastName.isEmpty()) {
                    map.put("last_name", lastName);
                }

                if (!email.isEmpty()) {
                    map.put("email", email);
                }

                if (!image.isEmpty()) {
                    map.put("image", image);
                }

                APIManager.editCurrentUser(view.getContext(), EditProfileActivity.this, map);
            }
        });
    }

    @Override
    public void onResponse(String response) {
        try {
            Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error_edit, Toast.LENGTH_LONG).show();
    }
}