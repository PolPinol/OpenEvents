package com.androidpprog2.openevents.enroll;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.R;

public class EnrolledEventsHolder extends RecyclerView.ViewHolder {

    public EnrolledEventsHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.list_events, parent, false));
    }
}
