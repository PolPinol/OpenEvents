package com.androidpprog2.openevents.friendRequests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.entities.Event;
import com.androidpprog2.openevents.entities.FriendRequest;
import com.androidpprog2.openevents.friendRequests.FriendRequestAdapter;
import com.androidpprog2.openevents.myEvents.MyEventsAdapter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestsActivity extends AppCompatActivity implements ResponseListener {
    private final static int MODE_GET_API = 0;
    private final static int MODE_UPDATE_UI = 1;

    private int modeRequest;

    private RecyclerView recyclerView;
    private FriendRequestAdapter adapter;
    private List<FriendRequest> listFR;

    private String name;
    private String image;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_requests);
        getSupportActionBar().hide();

        listFR = new ArrayList<>();

        recyclerView = (RecyclerView) this.findViewById(R.id.my_friendsrequests_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FriendRequestAdapter(null, this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        modeRequest = MODE_GET_API;
        listFR = new ArrayList<>();
        APIManager.getFriendRequests(this, this);
    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new FriendRequestAdapter(listFR, this, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter = new FriendRequestAdapter(listFR, this, this);
            recyclerView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponse(String response) {
        if (modeRequest == MODE_GET_API) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    name = jsonArray.getJSONObject(i).getString("name");
                    image = jsonArray.getJSONObject(i).getString("image");
                    id = jsonArray.getJSONObject(i).getInt("id");

                    FriendRequest friendRequest = new FriendRequest(name, image, id);
                    listFR.add(friendRequest);
                }

                modeRequest = MODE_UPDATE_UI;
                updateUI();
            } catch (Exception e) {
                Toast.makeText(this, R.string.error_parsing_json, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, R.string.accept_fr, Toast.LENGTH_LONG).show();

            listFR = new ArrayList<>();
            updateUI();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
    }
}