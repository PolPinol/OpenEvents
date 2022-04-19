package com.androidpprog2.openevents.friendRequests;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.entities.Event;
import com.androidpprog2.openevents.entities.FriendRequest;
import com.androidpprog2.openevents.myEvents.MyEventHolder;

import java.util.List;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestHolder> {
    private List<FriendRequest> listFR;
    private Activity activity;
    private ResponseListener listener;

    public FriendRequestAdapter(List<FriendRequest> listFR, Activity activity, ResponseListener listener) {
        this.listFR = listFR;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public FriendRequestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new FriendRequestHolder(layoutInflater, parent, activity, listener);
    }

    @Override
    public void onBindViewHolder(FriendRequestHolder holder, int position) {
        FriendRequest friendRequest = listFR.get(position);
        holder.bind(friendRequest);
    }

    @Override
    public int getItemCount() {
        if (listFR == null) {
            return 0;
        } else {
            return listFR.size();
        }
    }
}
