package com.androidpprog2.openevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.myEvents.MyEventsActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEventActivity extends AppCompatActivity implements ResponseListener {
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
        setContentView(R.layout.activity_create_event);
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

                if (!name.isEmpty() && !image.isEmpty() && !location.isEmpty() && !description.isEmpty() && !startDate.isEmpty() &&
                !endDate.isEmpty() && !numPart.isEmpty() && !type.isEmpty()) {
                    try {
                        Integer.parseInt(numPart);

                        try {
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            Date date1 = df.parse(startDate);
                            Date date2 = df.parse(endDate);

                            if (date2.after(date1) || date2.equals(date1)) {
                                APIManager.postEvent(view.getContext(), CreateEventActivity.this, name, image, location, description, startDate, endDate, numPart, type);
                            } else {
                                Toast.makeText(view.getContext(), R.string.toast_date_endstart, Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e){
                            Toast.makeText(view.getContext(), R.string.toast_date_error, Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e){
                        Toast.makeText(view.getContext(), R.string.toast_integer_error, Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(view.getContext(), R.string.toast_empty, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onResponse(String response) {
        Intent intent = new Intent(CreateEventActivity.this, MyEventsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error_create_event, Toast.LENGTH_LONG).show();
    }
}