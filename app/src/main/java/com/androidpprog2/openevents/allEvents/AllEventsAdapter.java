package com.androidpprog2.openevents.allEvents;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.entities.Event;

import java.util.List;

public class AllEventsAdapter extends RecyclerView.Adapter<AllEventsHolder> {

    private List<Event> eventList;
    private Activity activity;

    public AllEventsAdapter(List<Event> listEvents, Activity activity) {
        this.eventList = listEvents;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AllEventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new AllEventsHolder(layoutInflater, parent, activity);
    }

    @Override
    public void onBindViewHolder(AllEventsHolder holder, int position) {
        Event event = eventList.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return eventList == null ? 0 : eventList.size();
    }
}
