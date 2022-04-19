package com.androidpprog2.openevents.friendRequests;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.entities.FriendRequest;
import com.squareup.picasso.Picasso;

public class FriendRequestHolder extends RecyclerView.ViewHolder {
    private final static String NO_IMAGE_URL = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";

    private TextView nameEventText;
    private ImageView imageView;
    private int id;
    private Button acceptButton;
    private Button declineButton;

    private Activity activity;


    public FriendRequestHolder(LayoutInflater inflater, ViewGroup parent, Activity activity, ResponseListener listener) {
        super(inflater.inflate(R.layout.list_friend_requests, parent, false));

        nameEventText = (TextView) itemView.findViewById(R.id.name_fr);
        imageView = (ImageView) itemView.findViewById(R.id.image_fr);

        acceptButton = itemView.findViewById(R.id.accept_button);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIManager.acceptFriendshipRequest(itemView.getContext(), listener, id);
            }
        });

        declineButton = itemView.findViewById(R.id.decline_button);
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIManager.declineFriendshipRequest(itemView.getContext(), listener, id);
            }
        });

        this.activity = activity;
    }

    public void bind(FriendRequest friendRequest) {
        nameEventText.setText(friendRequest.getName());
        id = friendRequest.getId();

        try {
            Picasso.get().load(friendRequest.getImage()).into(imageView);
        } catch (Exception e) {
            Picasso.get().load(NO_IMAGE_URL).into(imageView);
        }
    }
}
