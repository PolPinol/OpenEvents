package com.androidpprog2.openevents.enroll;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.allEvents.AllEventsHolder;
import com.androidpprog2.openevents.entities.Event;


import java.util.List;

public class EnrolledEventsAdapter extends RecyclerView.Adapter<AllEventsHolder>{
    private List<Event> listEvents;
    private Activity activity;

    public EnrolledEventsAdapter(List<Event> listEvents, Activity activity) {
        this.listEvents = listEvents;
        this.activity = activity;
    }

    @Override
    public AllEventsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new AllEventsHolder(layoutInflater, parent, activity);
    }

    @Override
    public void onBindViewHolder(AllEventsHolder holder, int position) {
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
