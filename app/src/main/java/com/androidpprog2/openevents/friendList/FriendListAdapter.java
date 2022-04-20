package com.androidpprog2.openevents.friendList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.entities.Friend;
import com.androidpprog2.openevents.entities.FriendRequest;
import com.androidpprog2.openevents.friendRequests.FriendRequestHolder;

import java.util.List;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListHolder> {
    private List<Friend> listFR;
    private Activity activity;

    public FriendListAdapter(List<Friend> listFR, Activity activity) {
        this.listFR = listFR;
        this.activity = activity;
    }

    @Override
    public FriendListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new FriendListHolder(layoutInflater, parent, activity);
    }

    @Override
    public void onBindViewHolder(FriendListHolder holder, int position) {
        Friend friend = listFR.get(position);
        holder.bind(friend);
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
