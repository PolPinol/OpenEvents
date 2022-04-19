package com.androidpprog2.openevents.chat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.entities.Friend;
import com.androidpprog2.openevents.entities.Message;
import com.androidpprog2.openevents.friendList.FriendListHolder;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {
    private List<Message> listMessage;
    private Activity activity;

    public ChatAdapter(List<Message> listMessage, Activity activity) {
        this.listMessage = listMessage;
        this.activity = activity;
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new ChatHolder(layoutInflater, parent, activity);
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        Message message = listMessage.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        if (listMessage == null) {
            return 0;
        } else {
            return listMessage.size();
        }
    }
}