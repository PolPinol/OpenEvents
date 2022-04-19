package com.androidpprog2.openevents;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ViewEventsActivity extends AppCompatActivity {
    //Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_events);
        getSupportActionBar().hide();

        /*startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateEventActivity.this, ???.class);
                startActivity(intent);
            }
        });*/

    }
}