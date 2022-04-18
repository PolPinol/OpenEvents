package com.androidpprog2.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageEventsActivity extends AppCompatActivity {
    Button createEventButton;
    Button editEventButton;
    Button viewEventsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_events);

        createEventButton = findViewById(R.id.create_event_button);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageEventsActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });

        editEventButton = findViewById(R.id.edit_event_button);
        editEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageEventsActivity.this, EditEventActivity.class);
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