package com.androidpprog2.openevents.myEvents;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.entities.Event;

import java.util.List;

public class MyEventsAdapter extends RecyclerView.Adapter<MyEventHolder> {
    private List<Event> listEvents;
    private Activity activity;

    public MyEventsAdapter(List<Event> listEvents, Activity activity) {
        this.listEvents = listEvents;
        this.activity = activity;
    }

    @Override
    public MyEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new MyEventHolder(layoutInflater, parent, activity);
    }

    @Override
    public void onBindViewHolder(MyEventHolder holder, int position) {
        Event event = listEvents.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        if (listEvents == null) {
            return 0;
        } else {
            return listEvents.size();
        }
    }
}
