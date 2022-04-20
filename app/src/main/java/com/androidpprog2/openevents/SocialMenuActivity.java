package com.androidpprog2.openevents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.androidpprog2.openevents.friendList.FriendListActivity;
import com.androidpprog2.openevents.friendRequests.FriendRequestsActivity;

public class SocialMenuActivity extends AppCompatActivity {
    Button searchUserButton;
    Button friendRequestButton;
    Button friendListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_menu);
        getSupportActionBar().hide();

        searchUserButton = findViewById(R.id.search_user_button);
        searchUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SocialMenuActivity.this, SearchUserActivity.class);
                startActivity(intent);
            }
        });

        friendRequestButton = findViewById(R.id.manage_friend_requests_button);
        friendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SocialMenuActivity.this, FriendRequestsActivity.class);
                startActivity(intent);
            }
        });

        friendListButton = findViewById(R.id.chat_button);
        friendListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SocialMenuActivity.this, FriendListActivity.class);
                startActivity(intent);
            }
        });
    }
}