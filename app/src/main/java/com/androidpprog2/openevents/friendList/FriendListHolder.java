package com.androidpprog2.openevents.friendList;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.DetailEventActivity;
import com.androidpprog2.openevents.MenuActivity;
import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.SocialMenuActivity;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.chat.ChatActivity;
import com.androidpprog2.openevents.entities.Friend;
import com.androidpprog2.openevents.entities.FriendRequest;
import com.squareup.picasso.Picasso;

public class FriendListHolder extends RecyclerView.ViewHolder {
    private final static String NO_IMAGE_URL = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";

    private TextView nameEventText;
    private ImageView imageView;
    private int id;

    private Button chatButton;

    private Activity activity;


    public FriendListHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.list_friend, parent, false));

        nameEventText = (TextView) itemView.findViewById(R.id.name_friend);
        imageView = (ImageView) itemView.findViewById(R.id.image_friend);

        chatButton = itemView.findViewById(R.id.chat_button);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ChatActivity.class);
                intent.putExtra("ARGUMENT_OTHER_ID", id);
                activity.startActivity(intent);
            }
        });

        this.activity = activity;
    }

    public void bind(Friend friend) {
        nameEventText.setText(friend.getName());
        id = friend.getId();

        try {
            Picasso.get().load(friend.getImage()).into(imageView);
        } catch (Exception e) {
            Picasso.get().load(NO_IMAGE_URL).into(imageView);
        }
    }
}
