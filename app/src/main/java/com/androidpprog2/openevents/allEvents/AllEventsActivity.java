package com.androidpprog2.openevents.allEvents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.CreateEventActivity;
import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.ViewMyEventsActivity;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.entities.Event;
import com.androidpprog2.openevents.myEvents.MyEventsAdapter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllEventsActivity extends AppCompatActivity implements ResponseListener {
    // Buttons
    private Button currentEventsButton;
    private Button categoryButton;
    private Button filtersButton;
    private Button popularityButton;

    // Edit Text fields
    private EditText typeField;
    private EditText locationField;
    private EditText eventNameField;
    private EditText dateField;

    // Connecting recycler view attributes
    private RecyclerView recyclerView;
    private AllEventsAdapter adapter;
    private List<Event> eventList;

    // Declaring mode constants
    private final static int MODE_CURRENT_EVENTS = 0;
    private final static int MODE_CATEGORY = 1;
    private final static int MODE_FILTERS = 2;
    private final static int MODE_POPULARITY = 3;
    private int mode;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_events);
        getSupportActionBar().hide();
        this.activity = this;

        // Connecting Recycler View elements
        eventList = new ArrayList<>();
        recyclerView = (RecyclerView) this.findViewById(R.id.all_events_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AllEventsAdapter(null, this);
        recyclerView.setAdapter(adapter);

        // We connect the edit fields
        typeField = findViewById(R.id.type_input_events);
        locationField = findViewById(R.id.location_input);
        eventNameField = findViewById(R.id.event_name_input);
        dateField = findViewById(R.id.date_input);

        currentEventsButton = findViewById(R.id.current_events_button);
        categoryButton = findViewById(R.id.category_button);
        filtersButton = findViewById(R.id.filters_button);
        popularityButton = findViewById(R.id.popularity_button);

        onCurrentEventsClick();
        currentEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCurrentEventsClick();
                mode = MODE_CURRENT_EVENTS;
                eventList = new ArrayList<>();
                APIManager.getAllCurrentEvent(view.getContext(), AllEventsActivity.this);
                updateUI();

            }
        });

        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeField.setVisibility(View.VISIBLE);
                locationField.setVisibility(View.GONE);
                eventNameField.setVisibility(View.GONE);
                dateField.setVisibility(View.GONE);
                typeField.getText().clear();

                //Personalising selected color button
                currentEventsButton.setTextColor(getColor(R.color.mid_grey));
                categoryButton.setTextColor(getColor(R.color.black));
                filtersButton.setTextColor(getColor(R.color.mid_grey));
                popularityButton.setTextColor(getColor(R.color.mid_grey));

                // Realitzar la filtració per categoria
                mode = MODE_CATEGORY;
                eventList = new ArrayList<>();
                updateUI();
            }
        });

        filtersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeField.setVisibility(View.GONE);
                locationField.setVisibility(View.VISIBLE);
                eventNameField.setVisibility(View.VISIBLE);
                dateField.setVisibility(View.VISIBLE);

                locationField.getText().clear();
                locationField.getText().clear();
                eventNameField.getText().clear();

                //Personalising selected color button
                currentEventsButton.setTextColor(getColor(R.color.mid_grey));
                categoryButton.setTextColor(getColor(R.color.mid_grey));
                filtersButton.setTextColor(getColor(R.color.black));
                popularityButton.setTextColor(getColor(R.color.mid_grey));

                eventList = new ArrayList<>();
                updateUI();

            }
        });

        locationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String location = locationField.getText().toString();
                String eventName = eventNameField.getText().toString();
                String dataField = dateField.getText().toString();

                if (!dataField.isEmpty() || !location.isEmpty() || !eventName.isEmpty()) {
                    eventList = new ArrayList<>();
                    mode = MODE_FILTERS;
                    APIManager.getEventsSearch(activity, AllEventsActivity.this, location, eventName, dataField);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        dateField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String location = locationField.getText().toString();
                String eventName = eventNameField.getText().toString();
                String dataField = dateField.getText().toString();

                if (!dataField.isEmpty() || !location.isEmpty() || !eventName.isEmpty()) {
                    eventList = new ArrayList<>();
                    mode = MODE_FILTERS;
                    APIManager.getEventsSearch(activity, AllEventsActivity.this, location, eventName, dataField);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        typeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String type = typeField.getText().toString();

                if (!type.isEmpty()) {
                    eventList = new ArrayList<>();
                    mode = MODE_CATEGORY;
                    APIManager.getAllCurrentEvent(activity, AllEventsActivity.this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        eventNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String location = locationField.getText().toString();
                String eventName = eventNameField.getText().toString();
                String dataField = dateField.getText().toString();

                if (!dataField.isEmpty() || !location.isEmpty() || !eventName.isEmpty()) {
                    eventList = new ArrayList<>();
                    mode = MODE_FILTERS;
                    APIManager.getEventsSearch(activity, AllEventsActivity.this, location, eventName, dataField);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        popularityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeField.setVisibility(View.GONE);
                locationField.setVisibility(View.GONE);
                eventNameField.setVisibility(View.GONE);
                dateField.setVisibility(View.GONE);

                //Personalising selected color button
                currentEventsButton.setTextColor(getColor(R.color.mid_grey));
                categoryButton.setTextColor(getColor(R.color.mid_grey));
                filtersButton.setTextColor(getColor(R.color.mid_grey));
                popularityButton.setTextColor(getColor(R.color.black));

                // Realitzar el GET /events/best
                mode = MODE_POPULARITY;
                eventList = new ArrayList<>();
                APIManager.getEventsBest(view.getContext(), AllEventsActivity.this);

                updateUI();
            }
        });

    }

    private void onCurrentEventsClick() {
        // Hiding unnecessary edit fields
        typeField.setVisibility(View.GONE);
        locationField.setVisibility(View.GONE);
        eventNameField.setVisibility(View.GONE);
        dateField.setVisibility(View.GONE);

        //Personalising selected color button
        currentEventsButton.setTextColor(getColor(R.color.black));
        categoryButton.setTextColor(getColor(R.color.mid_grey));
        filtersButton.setTextColor(getColor(R.color.mid_grey));
        popularityButton.setTextColor(getColor(R.color.mid_grey));

        // Realitzar el GET /events
    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new AllEventsAdapter(eventList, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter = new AllEventsAdapter(eventList, this);
            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventList = new ArrayList<>();
        mode = MODE_CURRENT_EVENTS;
        APIManager.getAllCurrentEvent(this, this);
    }

    @Override
    public void onResponse(String response) {
        String name, image, location, description;
        String startDate, endDate, numPart, type;
        int id, owner_id;

        switch (mode) {
            case MODE_CURRENT_EVENTS:
            case MODE_FILTERS:
            case MODE_POPULARITY:
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        name = jsonArray.getJSONObject(i).getString("name");
                        image = jsonArray.getJSONObject(i).getString("image");
                        location = jsonArray.getJSONObject(i).getString("location");
                        description = jsonArray.getJSONObject(i).getString("description");
                        startDate = jsonArray.getJSONObject(i).getString("eventStart_date");
                        endDate = jsonArray.getJSONObject(i).getString("eventEnd_date");
                        numPart = jsonArray.getJSONObject(i).getString("n_participators");
                        type = jsonArray.getJSONObject(i).getString("type");
                        id = Integer.parseInt(jsonArray.getJSONObject(i).getString("id"));
                        owner_id = Integer.parseInt(jsonArray.getJSONObject(i).getString("owner_id"));

                        Event event = new Event(name, image, location, description, startDate, endDate, numPart, type, id, owner_id);
                        eventList.add(event);

                    }

                    updateUI();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                break;
            case MODE_CATEGORY:

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        name = jsonArray.getJSONObject(i).getString("name");
                        image = jsonArray.getJSONObject(i).getString("image");
                        location = jsonArray.getJSONObject(i).getString("location");
                        description = jsonArray.getJSONObject(i).getString("description");
                        startDate = jsonArray.getJSONObject(i).getString("eventStart_date");
                        endDate = jsonArray.getJSONObject(i).getString("eventEnd_date");
                        numPart = jsonArray.getJSONObject(i).getString("n_participators");
                        type = jsonArray.getJSONObject(i).getString("type");
                        id = Integer.parseInt(jsonArray.getJSONObject(i).getString("id"));
                        owner_id = Integer.parseInt(jsonArray.getJSONObject(i).getString("owner_id"));

                        if (type.contains(typeField.getText().toString())) {
                            Event event = new Event(name, image, location, description, startDate, endDate, numPart, type, id, owner_id);
                            eventList.add(event);
                        }
                    }

                    updateUI();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
    }
}