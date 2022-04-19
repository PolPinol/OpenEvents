package com.androidpprog2.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

public class SearchUserActivity extends AppCompatActivity implements ResponseListener {
    private TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        getSupportActionBar().hide();

        emailTextView = findViewById(R.id.name_input_search);

        Button searchUserButton = findViewById(R.id.search_button_search);
        searchUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTextView.getText().toString();
                if (!email.isEmpty()) {
                    APIManager.getUsersFiltered(view.getContext(), SearchUserActivity.this, email);
                } else {
                    Toast.makeText(view.getContext(), R.string.toast_empty, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);

            if (jsonArray.length() > 0) {
                int id = jsonArray.getJSONObject(0).getInt("id");
                Intent intent = new Intent(SearchUserActivity.this, SearchedUserProfile.class);
                intent.putExtra("ARGUMENT_OBJECT_ID", id);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.toast_error_user_exists, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.toast_error_user_exists, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
    }
}