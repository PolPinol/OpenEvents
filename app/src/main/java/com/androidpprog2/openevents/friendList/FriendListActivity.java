package com.androidpprog2.openevents.friendList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.entities.Friend;
import com.androidpprog2.openevents.entities.FriendRequest;
import com.androidpprog2.openevents.friendRequests.FriendRequestAdapter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class FriendListActivity extends AppCompatActivity implements ResponseListener {
    private RecyclerView recyclerView;
    private FriendListAdapter adapter;
    private List<Friend> listFR;

    private String name;
    private String image;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        getSupportActionBar().hide();

        listFR = new ArrayList<>();

        recyclerView = (RecyclerView) this.findViewById(R.id.my_friends_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FriendListAdapter(null, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        listFR = new ArrayList<>();
        APIManager.getAllFriends(this, this);
    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new FriendListAdapter(listFR, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter = new FriendListAdapter(listFR, this);
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
                id = jsonArray.getJSONObject(i).getInt("id");

                Friend friend = new Friend(name, image, id);
                listFR.add(friend);
            }

            updateUI();
        } catch (Exception e) {
            Toast.makeText(this, R.string.error_parsing_json, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
    }
}