package com.androidpprog2.openevents.enroll;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.VolleyError;
import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.entities.Event;
import com.androidpprog2.openevents.entities.Friend;
import com.androidpprog2.openevents.friendList.FriendListAdapter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class EnrolledEventsActivity extends AppCompatActivity implements ResponseListener {
    private RecyclerView recyclerView;
    private EnrolledEventsAdapter adapter;

    private List<Event> listEvent;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_enrolled);
        getSupportActionBar().hide();

        listEvent = new ArrayList<>();
        recyclerView = (RecyclerView) this.findViewById(R.id.enroll_recycle_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EnrolledEventsAdapter(null, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        listEvent = new ArrayList<>();
        APIManager.getAllEventsWithAssistanceFromUser(this, this, APIManager.getId());
    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new EnrolledEventsAdapter(listEvent, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter = new EnrolledEventsAdapter(listEvent, this);
            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                if (!jsonArray.getJSONObject(i).has("owner_id") || jsonArray.getJSONObject(i).isNull("owner_id")) {
                    // Solve error
                    continue;
                }

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
                listEvent.add(event);
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
