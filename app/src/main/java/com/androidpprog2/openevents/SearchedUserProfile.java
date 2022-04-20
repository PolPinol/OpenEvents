package com.androidpprog2.openevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchedUserProfile extends AppCompatActivity implements ResponseListener {
    private final static int USER_INFO = 0;
    private final static int USER_STATS = 1;
    private final static int ADD_FRIEND = 2;
    private final static String NO_IMAGE_URL = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";

    private TextView nameTextView;
    private TextView emailTextView;
    private TextView lastNameTextView;
    private ImageView imageTextView;
    private TextView avgScoreTextView;
    private TextView numCommsTextView;
    private TextView perctTextView;
    private String name;
    private String email;
    private String lastName;
    private String image;
    private String avgScore;
    private String numComms;
    private String perct;
    private int modeResponse;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_user_profile);
        getSupportActionBar().hide();

        this.id = getIntent().getExtras().getInt("ARGUMENT_OBJECT_ID");

        nameTextView = findViewById(R.id.name_event_show);
        lastNameTextView = findViewById(R.id.location_text_show);
        emailTextView = findViewById(R.id.descrption_show_text);
        avgScoreTextView = findViewById(R.id.startdate_show);
        numCommsTextView = findViewById(R.id.endDate_show);
        perctTextView = findViewById(R.id.numpart_show);
        imageTextView = findViewById(R.id.image_event_show);

        modeResponse = USER_INFO;
        APIManager.getUserById(this, this, this.id);

        // button add friend
        Button addFriend = findViewById(R.id.add_friend_btn);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeResponse = ADD_FRIEND;
                APIManager.createFriendship(view.getContext(), SearchedUserProfile.this, id);
            }
        });
    }

    @Override
    public void onResponse(String response) {
        if (modeResponse == USER_INFO) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                name = jsonArray.getJSONObject(0).getString("name");
                email = jsonArray.getJSONObject(0).getString("email");
                lastName = jsonArray.getJSONObject(0).getString("last_name");
                image = jsonArray.getJSONObject(0).getString("image");

                nameTextView.setText(name);
                lastNameTextView.setText(lastName);
                emailTextView.setText(email);

                try {
                    Picasso.get().load(image).into(imageTextView);
                } catch (Exception e) {
                    Picasso.get().load(NO_IMAGE_URL).into(imageTextView);
                }

                modeResponse = USER_STATS;
                APIManager.getUserStats(this, this, APIManager.getId());
            } catch (Exception e) {
                Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
            }
        } else if (modeResponse == USER_STATS) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                avgScore = jsonObject.getString("avg_score");
                numComms = jsonObject.getString("num_comments");
                perct = jsonObject.getString("percentage_commenters_below");

                avgScoreTextView.setText(avgScore);
                numCommsTextView.setText(numComms);
                perctTextView.setText(perct);
            } catch (Exception e) {
                Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
            }
        } else if (modeResponse == ADD_FRIEND) {
            Toast.makeText(this, R.string.add_friend_ok, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onError(VolleyError error) {
        if (modeResponse == ADD_FRIEND) {
            Toast.makeText(this, R.string.error_friend_api, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
        }
    }
}