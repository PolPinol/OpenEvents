package com.androidpprog2.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageEventsActivity extends AppCompatActivity {
    private Button createEventButton;
    private Button viewEventsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_events);
        getSupportActionBar().hide();

        createEventButton = findViewById(R.id.create_event_button);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageEventsActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });

        viewEventsButton = findViewById(R.id.view_events_button);
        viewEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageEventsActivity.this, ViewEventsActivity.class);
                startActivity(intent);
            }
        });

    }
}