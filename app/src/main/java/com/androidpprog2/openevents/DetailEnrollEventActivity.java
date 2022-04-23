package com.androidpprog2.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

public class DetailEnrollEventActivity extends AppCompatActivity implements ResponseListener {
    private final static String NO_IMAGE_URL = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";
    private final static int EVENT_INFO = 0;
    private final static int EVENT_REMOVE = 1;

    private int id_event;
    private int modeResponse;

    private TextView nameTextView;
    private ImageView imageTextView;
    private TextView locationTextView;
    private TextView descriptionTextView;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private TextView numPartTextView;
    private TextView typeTextView;

    private String name;
    private String image;
    private String location;
    private String description;
    private String startDate;
    private String endDate;
    private int numPart;
    private String type;

    private TextView enrolledText;
    private Button enrollButton;
    private Button commentsButton;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_enroll_event);
        getSupportActionBar().hide();

        imageTextView = findViewById(R.id.image_event_show);
        nameTextView = findViewById(R.id.name_event_show);
        locationTextView = findViewById(R.id.location_text_show);
        descriptionTextView = findViewById(R.id.descrption_show_text);
        startDateTextView = findViewById(R.id.startdate_show);
        endDateTextView = findViewById(R.id.endDate_show);
        numPartTextView = findViewById(R.id.numpart_show);
        typeTextView = findViewById(R.id.type_event_text);

        enrolledText = findViewById(R.id.enrolled_event_text);
        enrolledText.setVisibility(View.GONE);
        modeResponse = EVENT_INFO;
        this.id_event = getIntent().getExtras().getInt("ARGUMENT_EVENT_ID");
        APIManager.getEventById(this, this, id_event);

        enrollButton = findViewById(R.id.enroll_event_button);
        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enrollButton.getText().equals(getString(R.string.enroll_string))) {
                    enrollButton.setText(getString(R.string.unenroll_string));
                    enrolledText.setVisibility(View.VISIBLE);
                    // Modify API

                } else {
                    enrollButton.setText(getString(R.string.enroll_string));
                    enrolledText.setVisibility(View.GONE);

                    // Modify API

                }
            }
        });

        commentsButton = findViewById(R.id.comments_event_button);
        commentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // See comments on API
            }
        });

        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cridar a l'API per posar puntuació a l'event
            }
        });
    }


    @Override
    public void onResponse(String response) {
        if (modeResponse == EVENT_INFO) {
            try {
                JSONArray jsonArray = new JSONArray(response);

                name = jsonArray.getJSONObject(0).getString("name");
                image = jsonArray.getJSONObject(0).getString("image");
                location = jsonArray.getJSONObject(0).getString("location");
                description = jsonArray.getJSONObject(0).getString("description");
                startDate = jsonArray.getJSONObject(0).getString("eventStart_date");
                endDate = jsonArray.getJSONObject(0).getString("eventEnd_date");
                numPart = jsonArray.getJSONObject(0).getInt("n_participators");
                type = jsonArray.getJSONObject(0).getString("type");

                nameTextView.setText(name);

                try {
                    Picasso.get().load(image).into(imageTextView);
                } catch (Exception e) {
                    Picasso.get().load(NO_IMAGE_URL).into(imageTextView);
                }

                locationTextView.setText(location);
                descriptionTextView.setText(description);
                startDateTextView.setText(startDate);
                endDateTextView.setText(endDate);
                numPartTextView.setText(String.valueOf(numPart));
                typeTextView.setText(type);

                modeResponse = EVENT_REMOVE;
            } catch (Exception e) {
                Toast.makeText(this, R.string.error_parsing_json, Toast.LENGTH_LONG).show();
            }
        } else if (modeResponse == EVENT_REMOVE) {
            Toast.makeText(this, R.string.event_removed, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DetailEnrollEventActivity.this, ViewMyEventsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
    }
}