package com.androidpprog2.openevents.myEvents;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.DetailEventActivity;
import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.SearchUserActivity;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.entities.Event;
import com.squareup.picasso.Picasso;

public class MyEventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final static String NO_IMAGE_URL = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";

    private Event event;
    private TextView nameEventText;
    private TextView descriptionEventText;
    private ImageView imageView;
    private Activity activity;


    public MyEventHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.list_events, parent, false));

        nameEventText = (TextView) itemView.findViewById(R.id.name_event);
        descriptionEventText = (TextView) itemView.findViewById(R.id.description_event);
        imageView = (ImageView) itemView.findViewById(R.id.image_event);

        this.activity = activity;
        itemView.setOnClickListener(this);

    }

    public void bind(Event event) {
        this.event = event;
        nameEventText.setText(event.getName());
        descriptionEventText.setText(event.getDescription());

        try {
            Picasso.get().load(event.getImage()).into(imageView);
        } catch (Exception e) {
            Picasso.get().load(NO_IMAGE_URL).into(imageView);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(activity, DetailEventActivity.class);
        intent.putExtra("ARGUMENT_EVENT_ID", event.getId());

        activity.startActivity(intent);
    }
}
