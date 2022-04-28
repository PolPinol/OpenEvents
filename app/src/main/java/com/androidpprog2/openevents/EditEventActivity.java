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

import java.util.HashMap;
import java.util.Map;

public class EditEventActivity extends AppCompatActivity implements ResponseListener {
    private EditText nameText;
    private EditText imageText;
    private EditText locationText;
    private EditText descriptionText;
    private EditText startDateText;
    private EditText endDateText;
    private EditText numPartText;
    private EditText typeText;
    private String name;
    private String image;
    private String location;
    private String description;
    private String startDate;
    private String endDate;
    private String numPart;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        getSupportActionBar().hide();

        nameText = findViewById(R.id.name_input_create_2);
        imageText = findViewById(R.id.image_input_create_2);
        locationText = findViewById(R.id.location_input_create_2);
        descriptionText = findViewById(R.id.description_input_create_2);
        startDateText = findViewById(R.id.starting_date_input_create_2);
        endDateText = findViewById(R.id.finishing_date_input_create_2);
        numPartText = findViewById(R.id.number_of_participants_input_create_2);
        typeText = findViewById(R.id.type_input_create_2);

        Button createButton = findViewById(R.id.editButtonEvent);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameText.getText().toString();
                image = imageText.getText().toString();
                location = locationText.getText().toString();
                description = descriptionText.getText().toString();
                startDate = startDateText.getText().toString();
                endDate = endDateText.getText().toString();
                numPart = numPartText.getText().toString();
                type = typeText.getText().toString();

                Map<String, String> map = new HashMap<>();

                if (!name.isEmpty()) {
                    map.put("name", name);
                }

                if (!image.isEmpty()) {
                    map.put("image", image);
                }

                if (!location.isEmpty()) {
                    map.put("location", location);
                }

                if (!description.isEmpty()) {
                    map.put("description", description);
                }

                if (!startDate.isEmpty()) {
                    map.put("eventStart_date", startDate);
                }

                if (!endDate.isEmpty()) {
                    map.put("eventEnd_date", endDate);
                }

                if (!numPart.isEmpty()) {
                    map.put("n_participators", numPart);
                }

                if (!type.isEmpty()) {
                    map.put("type", type);
                }

                map.entrySet().forEach(entry -> {
                    Log.e("map", entry.getKey() + " " + entry.getValue());
                });
                int id_event = getIntent().getExtras().getInt("ARGUMENT_EVENT_ID");
                Log.e("eventid", String.valueOf(id_event));

                try {
                    if (!numPart.isEmpty()) {
                        Integer.parseInt(numPart);
                    }

                    APIManager.putEventById(view.getContext(), EditEventActivity.this, id_event, map);
                } catch (Exception e){
                    Toast.makeText(view.getContext(), R.string.toast_integer_error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onResponse(String response) {
        Log.e("resp", response);
        Intent intent = new Intent(EditEventActivity.this, ViewMyEventsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error_edit, Toast.LENGTH_LONG).show();
    }
}