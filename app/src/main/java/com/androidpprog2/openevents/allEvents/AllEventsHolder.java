package com.androidpprog2.openevents.allEvents;

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
import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.entities.Event;
import com.squareup.picasso.Picasso;

public class AllEventsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final static String NO_IMAGE_URL = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";

    //Attributes
    private ImageView eventImage;
    private TextView description;
    private TextView nameEvent;
    private Activity activity;
    private Event event;

    public AllEventsHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.list_my_events, parent, false));

        eventImage = (ImageView) itemView.findViewById(R.id.image_event);
        nameEvent = (TextView) itemView.findViewById(R.id.name_event);
        description = (TextView) itemView.findViewById(R.id.description_event);

        this.activity = activity;
        itemView.setOnClickListener(this);
    }

    public void bind(Event event) {
        this.event = event;
        nameEvent.setText(event.getName());
        description.setText(event.getDescription());

        try {
            Picasso.get().load(event.getImage()).into(eventImage);
        } catch (Exception e) {
            Picasso.get().load(NO_IMAGE_URL).into(eventImage);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(activity, DetailEventActivity.class);
        intent.putExtra("ARGUMENT_EVENT_ID", event.getId());

        activity.startActivity(intent);
    }
}
