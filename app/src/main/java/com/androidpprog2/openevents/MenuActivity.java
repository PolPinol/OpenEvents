package com.androidpprog2.openevents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.androidpprog2.openevents.allEvents.AllEventsActivity;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.myEvents.MyEventsActivity;

public class MenuActivity extends AppCompatActivity {
    private Button manageEventsButton;
    private Button searchButton;
    private Button profileButton;
    private Button logoutButton;
    private Button allEventsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        manageEventsButton = findViewById(R.id.manage_events_button);
        manageEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MyEventsActivity.class);
                startActivity(intent);
            }
        });

        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SocialMenuActivity.class);
                startActivity(intent);
            }
        });

        profileButton = findViewById(R.id.profile_button);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        allEventsButton = findViewById(R.id.all_events_button);
        allEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AllEventsActivity.class);
                startActivity(intent);
            }
        });


        logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIManager.logout();

                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor prefsEditor = sharedPrefs.edit();

                prefsEditor.putString("token", "");
                prefsEditor.apply();

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}