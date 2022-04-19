package com.androidpprog2.openevents.myEvents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.androidpprog2.openevents.CreateEventActivity;
import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.ViewMyEventsActivity;

public class MyEventsActivity extends AppCompatActivity {
    private Button createEventButton;
    private Button viewEventsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        getSupportActionBar().hide();

        createEventButton = findViewById(R.id.create_event_button);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyEventsActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });

        viewEventsButton = findViewById(R.id.view_events_button);
        viewEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyEventsActivity.this, ViewMyEventsActivity.class);
                startActivity(intent);
            }
        });
    }
}