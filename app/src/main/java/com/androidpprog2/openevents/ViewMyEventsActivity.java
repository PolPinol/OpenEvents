package com.androidpprog2.openevents;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.entities.Event;
import com.androidpprog2.openevents.myEvents.MyEventsAdapter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ViewMyEventsActivity extends AppCompatActivity implements ResponseListener {
    private RecyclerView recyclerView;
    private MyEventsAdapter adapter;
    private List<Event> listEvents;

    private String name;
    private String image;
    private String location;
    private String description;
    private String startDate;
    private String endDate;
    private String numPart;
    private String type;

    private int id;
    private int owner_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_events);
        getSupportActionBar().hide();

        listEvents = new ArrayList<>();

        recyclerView = (RecyclerView) this.findViewById(R.id.my_events_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyEventsAdapter(null, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        listEvents = new ArrayList<>();
        APIManager.getAllEvent(this, this);
    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new MyEventsAdapter(listEvents, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter = new MyEventsAdapter(listEvents, this);
            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponse(String response) {
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

                if (owner_id == APIManager.getId()) {
                    Event event = new Event(name, image, location, description, startDate, endDate, numPart, type, id, owner_id);
                    listEvents.add(event);
                }
            }

            updateUI();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
    }
}