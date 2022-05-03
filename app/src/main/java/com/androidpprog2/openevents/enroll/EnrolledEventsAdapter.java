package com.androidpprog2.openevents.enroll;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.entities.Event;
import com.androidpprog2.openevents.myEvents.MyEventHolder;


import java.util.List;

public class EnrolledEventsAdapter extends RecyclerView.Adapter<MyEventHolder>{
    private List<Event> listEvents;
    private Activity activity;

    public EnrolledEventsAdapter(List<Event> listEvents, Activity activity) {
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
